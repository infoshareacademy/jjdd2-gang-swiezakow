package pl.infoshareacademy;


public class SearchByQuestions {
    private Catalog catalog;

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public SearchResult chooseCategory(int categoryId) {
        boolean hasSubcategories = catalog.hasSubcategories(categoryId);
        if (hasSubcategories) {
            //AllegroCategory = First subcategory
            //return new SearchResult(...)

        }

        //Return link
        AllegroCategory category = catalog.findCategoryById(categoryId);
        return new SearchResult(category.getName(), category.getCatID(), true);
    }

    public SearchResult omitCategory(int categoryId) {
        //1. istnieje kolejna na tym smamym poziomie
        //2. nie istnieje kolejna ale jest parent
        //3. nie istnieje kolejna i nie ma parenta
        return null;
    }
}
