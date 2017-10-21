package pl.infoshareacademy.webapp.categoryPickerCommandWeb;

import com.google.common.collect.Iterables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroLink;
import pl.infoshareacademy.webapp.AllegroCategoryService;
import pl.infoshareacademy.webapp.promotedCategories.PromotedCategoriesService;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.ImageUrlService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class CategoryPickerCommandService {
    private final Logger logger = LogManager.getLogger(CategoryPickerCommandService.class);

    @Inject
    private AllegroCategoryService categoryService;

    @Inject
    private ImageUrlService urlService;

    @Inject
    private PromotedCategoriesService promotedCategoriesService;

    public List<PickerCommandCard> getMainCategories(int catId) {
        Map<Integer, List<AllegroCategory>> categoriesTree = categoryService.getCategoriesTree();
        List<AllegroCategory> categories = categoriesTree.get(catId);
        List<PickerCommandCard> mainCategories = new ArrayList<>();

        if (categories != null) {
            logger.info("Categories list was not empty");
            for (AllegroCategory category : categories) {
                AllegroCategory mainCategory = Iterables.getLast(categoryService.getAllParentsCategory(category));

                int mainCatId = mainCategory.getCatID();
                int catID = category.getCatID();
                String name = category.getName();
                logger.debug("MainCategoryId = " + mainCatId);
                logger.debug("Category ID = " +catID);
                logger.debug("Category name = " +name);

                boolean lastCategory = !categoriesTree.containsKey(catID);
                String link = lastCategory ? "#" : "/webapp/categoryPickerCommand?catId=" + catID;

                PickerCommandCard card = new PickerCommandCard(lastCategory, catID, name, urlService.getImageUrl(mainCatId),
                        AllegroLink.makeLink(name, catID), link, promotedCategoriesService.isCategoryPromoted(catID));
                mainCategories.add(card);
            }
        }
        mainCategories.sort((c1, c2) -> {
            if (c1.isPromoted() == c2.isPromoted()) {
                return 0;
            } else if (c1.isPromoted()) {
                return -1;
            } else {
                return 1;
            }
        });
        return mainCategories;
    }
}