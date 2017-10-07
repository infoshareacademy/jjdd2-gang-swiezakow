package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Singleton;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Singleton
public class Catalog {
    private static final Logger LOGGER = LogManager.getLogger(Catalog.class);
    public static final int ROOT_CATEGORY_ID = 0;

    private Map<Integer, List<AllegroCategory>> idToSubcategories = Collections.emptyMap();
    private Map<Integer, AllegroCategory> idToCategory = Collections.emptyMap();

    public Catalog() {
        ConfigurationLoader.loadConfiguration();
        Configuration config = ConfigurationLoader.getConfiguration();
        String categoryFilePath = config.getFilePath();

        try {
            updateCatalog(categoryFilePath);
        } catch (FileNotFoundException e) {
            LOGGER.error("Categories not found under {}. Putting default ones there.", categoryFilePath);
            try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("Allegro_cathegories_2016-02-13.xml")) {
                Files.copy(is, Paths.get(categoryFilePath));
                updateCatalog(categoryFilePath);
            } catch (IOException ioe) {
                LOGGER.error("Error while storing categories under {}", categoryFilePath, e);
                throw new RuntimeException("Cannot determine path for categories xml file. Issue with file " + categoryFilePath);
            }
        }
    }

    public void updateCatalog(String categoryFilePath) throws FileNotFoundException {
        if (!Files.exists(Paths.get(categoryFilePath))) {
            LOGGER.error("File not fount {}", categoryFilePath);
            throw new FileNotFoundException("File " + categoryFilePath + " not found");
        }

        LOGGER.info("Loading categories from {}", categoryFilePath);
        AllegroCategoryLoader loader = new AllegroCategoryLoader();
        idToSubcategories = loader.loadCategoryTree(categoryFilePath);
        initIdToCategory();
        LOGGER.info("Loaded {} categories", idToCategory.size());

    }

    public static Catalog catalogForFile(String categoryFilePath) {
        AllegroCategoryLoader loader = new AllegroCategoryLoader();
        Map<Integer, List<AllegroCategory>> map = loader.loadCategoryTree(categoryFilePath);
        return catalogForMap(map);
    }

    public static Catalog catalogForMap(Map<Integer, List<AllegroCategory>> idToSubcategories) {
        Catalog catalog = new Catalog();
        //Wewnatrz klasy mamy dostep do pol prywatnych
        catalog.idToSubcategories = idToSubcategories;
        catalog.initIdToCategory();
        return catalog;
    }

    private void initIdToCategory() {
        if (idToSubcategories == null || idToSubcategories.isEmpty()) {
            LOGGER.warn("no categories");
            return;
        }

        idToCategory = new HashMap<>();
        //Wszystkie listy podkategorii zawieraja wszystkie kategorie
        //kazda z nich zapisujemy pod id w idToCategory
        for (Map.Entry<Integer, List<AllegroCategory>> entry : idToSubcategories.entrySet()) {
            List<AllegroCategory> subcategories = entry.getValue();
            for (AllegroCategory category : subcategories) {
                idToCategory.put(category.getCatID(), category);
            }
        }
    }

    public AllegroCategory findCategoryById(int categoryId) {
        AllegroCategory result = idToCategory.get(categoryId);
        LOGGER.debug("for id = {} {} was returned", categoryId, result);
        return result;
    }

    public boolean hasSubcategories(int categoryId) {
        boolean result = idToSubcategories.containsKey(categoryId);
        LOGGER.debug(result ? " category with id= {} has subcategories" : "category with id= {} has no subcategories", categoryId);
        return result;
    }

    public List<AllegroCategory> getSubcategories(int categoryId) {
        List<AllegroCategory> result = idToSubcategories.get(categoryId);
        LOGGER.debug("category with id= {} subcategories: {}", categoryId, result);
        return result;
    }

    public Optional<AllegroCategory> findSibling(int categoryId) {
        AllegroCategory category = findCategoryById(categoryId);
        if (category == null) {
            LOGGER.warn("could not find category with id = {}", categoryId);
            return Optional.empty();
        }

        int parentId = category.getParent();
        List<AllegroCategory> parentSubcategories = getSubcategories(parentId);
        int currentCategoryIndex = parentSubcategories.indexOf(category);
        int nextCategoryIndex = currentCategoryIndex + 1;
        if (nextCategoryIndex < parentSubcategories.size()) {
            return Optional.of(parentSubcategories.get(nextCategoryIndex));
        } else {
            LOGGER.info("category with id = {} has no successor", categoryId);
            return Optional.empty();
        }
    }
}