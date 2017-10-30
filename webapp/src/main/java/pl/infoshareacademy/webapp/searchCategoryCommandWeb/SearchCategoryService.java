package pl.infoshareacademy.webapp.searchCategoryCommandWeb;

import com.google.common.collect.Iterables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroCategorySearcher;
import pl.infoshareacademy.SearchCategoryCommand;
import pl.infoshareacademy.webapp.AllegroCategoryService;
import pl.infoshareacademy.webapp.promotedCategories.PromotedCategoriesService;
import pl.infoshareacademy.webapp.redirect.RedirectService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class SearchCategoryService {

    private final static Logger logger = LogManager.getLogger(SearchCategoryService.class);

    @Inject
    private AllegroCategoryService categoryService;

    @Inject
    private PromotedCategoriesService promotedCategoriesService;

    @Inject
    private ImageUrlService imageUrlService;

    @Inject
    private RedirectService redirectService;

    private final AllegroCategorySearcher searcher = new AllegroCategorySearcher();

    public List<AllegroCategory> getMatchingCategories(String searchTerm) {
        List<AllegroCategory> allCategories = categoryService.getAllCategories();
        List<AllegroCategory> results = searcher.searchCategory(searchTerm, allCategories);

        logger.debug("Returned list of " + results.size() + " categories");
        return results;
    }

    public List<SearchResult> getSearchResults(String searchTerm) {
        List<AllegroCategory> matchingCategories = getMatchingCategories(searchTerm);

        return matchingCategories.stream()
                .map(ac -> {
                    List<AllegroCategory> allParentsCategory = categoryService.getAllParentsCategory(ac);
                    List<ParentAllegroLink> links = allParentsCategory.stream()
                            .map(parent -> new ParentAllegroLink(parent.getName(),
                                    redirectService.getSecretUrl(parent.getCatID(), 2, searchTerm)))
                            .collect(Collectors.toList());

                    AllegroCategory mainCategory = Iterables.getLast(allParentsCategory);

                    return new SearchResult(ac, links,
                            imageUrlService.getImageUrl(mainCategory.getCatID()),
                            promotedCategoriesService.isCategoryPromoted(ac.getCatID()),
                            redirectService.getSecretUrl(ac.getCatID(), 2,  searchTerm));
                })
                .sorted((sr1, sr2) -> {
                    if (sr1.isPromoted() == sr2.isPromoted()) {
                        return 0;
                    } else if (sr1.isPromoted()) {
                        return -1;
                    } else {
                        return 1;
                    }
                })
                .collect(Collectors.toList());
    }

}