package pl.infoshareacademy.webapp.allegro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.AllegroCategory;

import javax.ejb.Singleton;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Singleton
public class AllegroClient {
    private static final Logger logger = LogManager.getLogger(AllegroClient.class);
    private static final List<String> mainCategories = Arrays.asList("954b95b6-43cf-4104-8354-dea4d9b10ddf");

    private final BiMap<String, Integer> nonIntCategoryIdMap = HashBiMap.create();
    private int nextCategoryId = 1000000;

    public List<AllegroCategory> getAllCategoriesFromRest() {
        AllegroRestCategories allRestCategories = getAllRestCategories();
        return allRestCategories.getCategories().stream()
                .map(this::toAllegroCategory)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public String getRewrittenId(int id) {
        BiMap<Integer, String> map = nonIntCategoryIdMap.inverse();
        if (map.containsKey(id)) {
            return map.get(id);
        }
        return "" + id;
    }

    private AllegroCategory toAllegroCategory(AllegroRestCategory c) {
        String catId = c.getId();
        String parentId = c.getParent().getId();

        if (mainCategories.contains(catId)) {
            return null;
        }

        if (mainCategories.contains(parentId)) {
            parentId = "0";
        }

        int intCatId = toCatId(catId);
        int intParentId = toCatId(parentId);

        return new AllegroCategory(intCatId, c.getName(), intParentId, 0);
    }

    private int toCatId(String stringId) {
        try {
            return Integer.parseInt(stringId);
        } catch (NumberFormatException e) {
            if (nonIntCategoryIdMap.containsKey(stringId)) {
                return nonIntCategoryIdMap.get(stringId);
            }

            int catId = nextCategoryId++;
            nonIntCategoryIdMap.put(stringId, catId);
            logger.warn("Rewriting string id " + stringId + " to int id: " + catId);
            return catId;
        }
    }

    private AllegroRestCategories getAllRestCategories() {
        try {
            HttpResponse<String> response = Unirest.get("https://allegroapi.io/categories")
                    .queryString("tree", "listing-pl")
                    .header("Accept", "application/vnd.allegro.public.v1+json")
                    .asString();
            return parseAllegroResponse(response.getBody());
        } catch (UnirestException e) {
            logger.error("Cannot download categories", e);
            return new AllegroRestCategories(Collections.emptyList());
        }
    }

    private AllegroRestCategories parseAllegroResponse(String content) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.reader(AllegroRestCategories.class).readValue(
                    content);
        } catch (IOException e) {
            logger.error("caught an exception during loading allegro API", e);
            throw new RuntimeException(e);
        }
    }
}