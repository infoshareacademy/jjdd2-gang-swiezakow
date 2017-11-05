package pl.infoshareacademy.webapp.promotedCategories;

import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.webapp.AllegroCategoryService;
import pl.infoshareacademy.webapp.dao.PromotedCategoriesBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PromotedCategoriesService {

    @Inject
    private PromotedCategoriesBean promotedCategoriesBean;

    @Inject
    private AllegroCategoryService service;

    public void savePromotedCategories(Integer catId, List<Integer> ids) {
        List<AllegroCategory> categoriesForParent = service.getAllegroCategoriesForParent(catId);

        List<Integer> deleteCategories = categoriesForParent.stream()
                .map(AllegroCategory::getCatID)
                .collect(Collectors.toList());

        promotedCategoriesBean.deletePromotedCategories(deleteCategories);
        promotedCategoriesBean.savePromotedCategories(ids);
    }

    public PromotedCategoriesData getPromotedCategoriesData(Integer catId) {
        List<AllegroCategory> categoriesForParent = service.getAllegroCategoriesForParent(catId);
        List<Integer> promotedCategories = promotedCategoriesBean.getPromotedCategories();

        return new PromotedCategoriesData(categoriesForParent, promotedCategories);
    }

    public boolean isCategoryPromoted(Integer catId) {
        while (catId != 0) {
            List<Integer> promotedCategories = promotedCategoriesBean.getPromotedCategories();
            for (Integer category : promotedCategories) {
                if (category.equals(catId)) {
                    return true;
                }
            }
            AllegroCategory parentForCatId = service.getParentForCatId(catId);
            if (parentForCatId == null) {
                break;
            }
            catId = parentForCatId.getParent();
        }
        return false;
    }
}
