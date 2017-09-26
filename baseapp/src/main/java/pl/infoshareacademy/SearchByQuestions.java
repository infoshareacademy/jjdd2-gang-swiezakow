package pl.infoshareacademy;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchByQuestions {
    private static final Logger LOGGER = LogManager.getLogger(SearchByQuestions.class);

    private Catalog catalog;

    public SearchByQuestions(Catalog catalog) {
        this.catalog = catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public SearchResult chooseCategory(int categoryId) {
        boolean hasSubcategories = catalog.hasSubcategories(categoryId);
        //1. Ma podkategorie
        if (hasSubcategories) {
            AllegroCategory firstSubcategory = catalog.getSubcategories(categoryId).get(0);
            SearchResult result = new SearchResult(firstSubcategory.getName(), firstSubcategory.getCatID(), false);
            LOGGER.debug("navigating to subcategory {} of category {}", firstSubcategory.getCatID(), categoryId);
            return result;
        }

        //2 nie ma podkategorii
        AllegroCategory category = catalog.findCategoryById(categoryId);
        SearchResult result = new SearchResult(category.getName(), category.getCatID(), true);
        LOGGER.debug("returning a link for category {}", categoryId);
        return result;
    }

    public SearchResult omitCategory(int categoryId) {
        //1. istnieje kolejna na tym samym poziomie
        AllegroCategory nextCategory = catalog.findSibling(categoryId);
        if (nextCategory != null) {
            SearchResult result = new SearchResult(nextCategory.getName(), nextCategory.getCatID(), false);
            LOGGER.debug("ignoring category {}", categoryId);
            return result;
        }

        //2. nie istnieje kolejna ale jest parent
        AllegroCategory category = catalog.findCategoryById(categoryId);
        LOGGER.debug("looking for ancestor with successor");
        while (category.getParent() != catalog.ROOT_CATEGORY_ID) {
            LOGGER.debug("checking category with id {}", categoryId);
            AllegroCategory sibling = catalog.findSibling(category.getParent());
            if (sibling != null) {
                SearchResult result = new SearchResult(sibling.getName(), sibling.getCatID(), false);
                LOGGER.debug("successor with id {} found", sibling.getCatID());
                return result;
            }
            // idz do parenta parenta
            category = catalog.findCategoryById(category.getParent());
        }

        //3. nie istnieje kolejna i nie ma parenta
        LOGGER.debug("no further categories");
        return null;
    }
}