package pl.infoshareacademy.webapp;

import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroLink;
import pl.infoshareacademy.webapp.redirect.RedirectService;
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
    private ImageUrlService imageUrlService;

    @Inject
    private RedirectService redirectService;

    private Random random = new Random();

    public DashboardItem randomImageGenerator() {
        List<AllegroCategory> allegroCategoryList = allegroCategoryService.getCategoriesTree().get(0);
        int i = numberGenerator(allegroCategoryList);
        int catID = allegroCategoryList.get(i).getCatID();
        String name = allegroCategoryList.get(i).getName();

        return new DashboardItem(name,
                imageUrlService.getImageUrl(catID),
                redirectService.getSecretUrl(catID, 0, ""));
    }

    private int numberGenerator(List<AllegroCategory> list) {

        return random.nextInt(list.size() - 1);
    }
}
