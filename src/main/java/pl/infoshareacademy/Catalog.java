package pl.infoshareacademy;

import java.util.List;

public class Catalog {
    //idToSubcategories - mapa kategoria do podkategorii - jak w SearchByQuestionCommand

    public Catalog() {

    }

    public AllegroCategory findCategoryById(int categoryId) {
        //przydalaby sie mapa id to category
        return null;
    }

    public boolean hasSubcategories(int categoryId) {
        return false;
    }

    public List<AllegroCategory> getSubcategories(int categoryId) {
        return  null;
    }
}
