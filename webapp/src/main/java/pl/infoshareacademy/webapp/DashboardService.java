package pl.infoshareacademy.webapp;

import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroLink;
import pl.infoshareacademy.webapp.dao.PromotedCategoriesBean;
import pl.infoshareacademy.webapp.dao.StatisticResult;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.ImageUrlService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@Stateless
public class DashboardService {
    @Inject
    private AllegroCategoryService allegroCategoryService;

    @Inject
    private PromotedCategoriesBean promotedCategoriesBean;

    @Inject
    private ImageUrlService imageUrlService;

    @Inject
    private StatisticsResultsBean resultsBean;

    private Random random = new Random();

    public DashboardItem randomImageGenerator() {
        List<AllegroCategory> allegroCategoryList = allegroCategoryService.getCategoriesTree().get(0);
        int i = numberGenerator(allegroCategoryList);
        int catID = allegroCategoryList.get(i).getCatID();
        String name = allegroCategoryList.get(i).getName();

        return new DashboardItem(name,
                imageUrlService.getImageUrl(catID),
                AllegroLink.makeLink(name, catID));
    }

    public DashboardItem getMostPopularCategory() {
        List<StatisticResult> mostPopularCategories = resultsBean.getMostPopularCategories();
        if(mostPopularCategories.isEmpty()) {
            return randomImageGenerator();
        }
        StatisticResult statisticResult = mostPopularCategories.get(0);
        AllegroCategory category = allegroCategoryService.getCategoryForId(Integer.parseInt(statisticResult.getName()));
        return new DashboardItem(category.getName(),
                imageUrlService.getImageUrl(category.getCatID()),
                AllegroLink.makeLink(category.getName(), category.getCatID()));
    }

    public DashboardItem getPromotedCategory() {
        List<Integer> promotedCategories = promotedCategoriesBean.getPromotedCategories();
        if(promotedCategories.isEmpty()) {
            return randomImageGenerator();
        }
        Integer catId = promotedCategories.get(0);
        AllegroCategory category = allegroCategoryService.getCategoryForId(catId);
        return new DashboardItem(category.getName(), imageUrlService.getImageUrl(category.getCatID()),
                AllegroLink.makeLink(category.getName(), category.getCatID()));
    }

    private int numberGenerator(List<AllegroCategory> list) {

        return random.nextInt(list.size() - 1);
    }
}