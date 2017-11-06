package pl.infoshareacademy.webapp.searchQueryCommandWeb;

import com.google.common.collect.Iterables;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.webapp.AllegroCategoryService;
import pl.infoshareacademy.webapp.promotedCategories.PromotedCategoriesService;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.ImageUrlService;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.SearchCategoryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class SearchQueryService {

    @Inject
    private AllegroCategoryService categoryService;

    @Inject
    private PromotedCategoriesService promotedCategoriesService;

    @Inject
    private SearchCategoryService searchCategoryService;

    @Inject
    private ImageUrlService urlService;

    public List<QueryCard> getQueryCards(String searchTerm) {
        List<AllegroCategory> matchingCategories = searchCategoryService.getMatchingCategories(searchTerm);
        return matchingCategories.stream()
                .map(category -> {
                    String categoryName = category.getName();
                    String phrase = getPhrase(category);
                    List<AllegroCategory> allParentsCategory = categoryService.getAllParentsCategory(category);
                    return new QueryCard(urlService.getImageUrl(Iterables.getLast(allParentsCategory).getCatID()),
                            categoryName,
                            promotedCategoriesService.isCategoryPromoted(category.getCatID()),
                            phrase);
                })
                .sorted((qc1, qc2)-> {
                    if (qc1.isPromoted() == qc2.isPromoted()) {
                        return 0;
                    } else if (qc1.isPromoted()) {
                        return -1;
                    } else {
                        return 1;
                    }
                })
                .collect(Collectors.toList());
    }

    private String getPhrase(AllegroCategory result) {
        int parent = result.getParent();
        AllegroCategory parentCategory = categoryService.getCategoryForId(parent);
        if(parentCategory == null) {
            return result.getName().toLowerCase();
        }
        return String.format("%s %s", parentCategory.getName(), result.getName()).toLowerCase();
    }
}