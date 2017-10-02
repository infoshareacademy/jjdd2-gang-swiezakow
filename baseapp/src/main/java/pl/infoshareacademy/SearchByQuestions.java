package pl.infoshareacademy;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class SearchByQuestions {
    private static final Logger LOGGER = LogManager.getLogger(SearchByQuestions.class);

    @Inject
    private Catalog catalog;

    public SearchByQuestions() {
    }

    public SearchByQuestions(Catalog catalog) {
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
        if (category == null) {
            return new SearchResult("Err", 0, false);
        }
        SearchResult result = new SearchResult(category.getName(), category.getCatID(), true);
        LOGGER.debug("returning a link for category {}", categoryId);
        return result;
    }

    public Optional<SearchResult> omitCategory(int categoryId) {
        //1. istnieje kolejna na tym samym poziomie
        Optional<AllegroCategory> nextCategory = catalog.findSibling(categoryId);
        if (nextCategory.isPresent()) {
            SearchResult result = new SearchResult(nextCategory.get().getName(), nextCategory.get().getCatID(), false);
            LOGGER.debug("ignoring category {}", categoryId);
            return Optional.of(result);
        }

        //2. nie istnieje kolejna ale jest parent
        AllegroCategory category = catalog.findCategoryById(categoryId);
        LOGGER.debug("looking for ancestor with successor");
        while (category.getParent() != Catalog.ROOT_CATEGORY_ID) {
            LOGGER.debug("checking category with id {}", categoryId);
            Optional<AllegroCategory> sibling = catalog.findSibling(category.getParent());
            if (sibling.isPresent()) {
                SearchResult result = new SearchResult(sibling.get().getName(), sibling.get().getCatID(), false);
                LOGGER.debug("successor with id {} found", sibling.get().getCatID());
                return Optional.of(result);
            }
            // idz do parenta parenta
            category = catalog.findCategoryById(category.getParent());
        }

        //3. nie istnieje kolejna i nie ma parenta
        LOGGER.debug("no further categories");
        return Optional.empty();
    }
}