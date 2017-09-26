package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {
    private static final Logger LOGGER = LogManager.getLogger(Catalog.class);
    public static final int ROOT_CATEGORY_ID = 0;

    private Map<Integer, List<AllegroCategory>> idToSubcategories = Collections.emptyMap();
    private Map<Integer, AllegroCategory> idToCategory = Collections.emptyMap();

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

    public AllegroCategory findSibling(int categoryId) {
        AllegroCategory category = findCategoryById(categoryId);
        if (category == null) {
            LOGGER.warn("could not find category with id = {}", categoryId);
            return null;
        }

        int parentId = category.getParent();
        List<AllegroCategory> parentSubcategories = getSubcategories(parentId);
        int currentCategoryIndex = parentSubcategories.indexOf(category);
        int nextCategoryIndex = currentCategoryIndex + 1;
        if (nextCategoryIndex < parentSubcategories.size()) {
            return parentSubcategories.get(nextCategoryIndex);
        } else {
            LOGGER.info("category with id = {} has no successor", categoryId);
            return null;
        }
    }
}