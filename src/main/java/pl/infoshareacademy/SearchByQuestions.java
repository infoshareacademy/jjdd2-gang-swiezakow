package pl.infoshareacademy;


public class SearchByQuestions {
    private Catalog catalog;

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public SearchResult chooseCategory(int categoryId) {
        AllegroCategory category = catalog.findCategoryById(categoryId);
        boolean isLink = !catalog.hasSubcategories(categoryId);
        return new SearchResult(category.getName(), category.getCatID(), isLink);
    }
}
