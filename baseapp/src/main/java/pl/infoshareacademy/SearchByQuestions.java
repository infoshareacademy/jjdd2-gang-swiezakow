package pl.infoshareacademy;


public class SearchByQuestions {
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
            return new SearchResult(firstSubcategory.getName(), firstSubcategory.getCatID(), false);
        }

        //2 nie ma podkategorii
        AllegroCategory category = catalog.findCategoryById(categoryId);
        return new SearchResult(category.getName(), category.getCatID(), true);
    }

    public SearchResult omitCategory(int categoryId) {
        //1. istnieje kolejna na tym smamym poziomie
        AllegroCategory nextCategory = catalog.findSibling(categoryId);
        if (nextCategory != null) {
            return new SearchResult(nextCategory.getName(), nextCategory.getCatID(), false);
        }

        //2. nie istnieje kolejna ale jest parent
        AllegroCategory category = catalog.findCategoryById(categoryId);
        if (category.getParent() != catalog.ROOT_CATEGORY_ID) {
            AllegroCategory nextParent = catalog.findSibling(category.getParent());
            if (nextParent != null) {
                return new SearchResult(nextParent.getName(), nextParent.getCatID(), false);
            }
        }

        //3. nie istnieje kolejna i nie ma parenta
        return null;
    }
}
