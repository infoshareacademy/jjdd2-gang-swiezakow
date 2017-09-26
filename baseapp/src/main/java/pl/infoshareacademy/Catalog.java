package pl.infoshareacademy;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {
    public static final int ROOT_CATEGORY_ID = 0;

    private Map<Integer, List<AllegroCategory>> idToSubcategories = Collections.emptyMap();
    private Map<Integer, AllegroCategory> idToCategory = Collections.emptyMap();

    public static Catalog catalogForFile(String categoryFilePath) {
        AllegroCategoryLoader loader = new AllegroCategoryLoader();
        return catalogForMap(loader.loadCategoryTree(categoryFilePath));
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
        return idToCategory.get(categoryId);
    }

    public boolean hasSubcategories(int categoryId) {
        return idToSubcategories.containsKey(categoryId);
    }

    public List<AllegroCategory> getSubcategories(int categoryId) {
        return idToSubcategories.get(categoryId);
    }

    public AllegroCategory findSibling(int categoryId) {
        AllegroCategory category = findCategoryById(categoryId);
        if (category == null) {
            return null;
        }

        int parentId = category.getParent();
        List<AllegroCategory> parentSubcategories = getSubcategories(parentId);
        int currentCategoryIndex = parentSubcategories.indexOf(category);
        int nextCategoryIndex = currentCategoryIndex + 1;
        if (nextCategoryIndex < parentSubcategories.size()) {
            return parentSubcategories.get(nextCategoryIndex);
        } else {
            return null;
        }
    }
}