package pl.infoshareacademy.webapp.SearchCategoryCommandWeb;

import pl.infoshareacademy.AllegroCategory;

import java.util.List;
import java.util.Optional;

public class MainCategory {
    public Optional<AllegroCategory> getMainCategory(AllegroCategory categoryResult, List<AllegroCategory> allCategories) {
        if (categoryResult.getParent() == 0) {
            return Optional.of(categoryResult);
        } else {
            int parent = categoryResult.getParent();
            for (AllegroCategory allCategory : allCategories) {
                if (allCategory.getCatID() == parent) {
                    return getMainCategory(allCategory, allCategories);
                }
            }
        }
        return Optional.empty();
    }
}
