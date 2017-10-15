package pl.infoshareacademy.webapp.promotedCategories;

import pl.infoshareacademy.AllegroCategory;

import java.util.List;

public class PromotedCategoriesData {

    private final List<AllegroCategory> categoriesForParent;
    private final List<Integer> promotedCategoriesIds;

    public List<AllegroCategory> getCategoriesForParent() {
        return categoriesForParent;
    }

    public List<Integer> getPromotedCategoriesIds() {
        return promotedCategoriesIds;
    }

    public PromotedCategoriesData(List<AllegroCategory> categoriesForParent, List<Integer> promotedCategoriesIds) {
        this.categoriesForParent = categoriesForParent;
        this.promotedCategoriesIds = promotedCategoriesIds;
    }
}
