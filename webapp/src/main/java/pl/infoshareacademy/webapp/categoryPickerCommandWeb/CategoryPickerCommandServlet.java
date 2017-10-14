package pl.infoshareacademy.webapp.categoryPickerCommandWeb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroCategoryLoader;
import pl.infoshareacademy.AllegroLink;
import pl.infoshareacademy.webapp.dao.StatisticsBean;
import pl.infoshareacademy.webapp.entities.Statistics;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.CategoryPicture;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.FileConfiguration;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.ImageFileParser;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.ImageUrl;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.MainCategory;
import pl.infoshareacademy.webapp.statistics.StatisticEvents;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/categoryPickerCommand")
public class CategoryPickerCommandServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(CategoryPickerCommandServlet.class);

    private final AllegroCategoryLoader loader = new AllegroCategoryLoader();
    private final ImageUrl imUrl = new ImageUrl();
    private final ImageFileParser parser = new ImageFileParser();
    private final FileConfiguration fileConfiguration = parser.loadingImageFile();
    private final MainCategory mainCategory = new MainCategory();

    @Inject
    private StatisticsBean statisticsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        statisticsBean.addStatistics(new Statistics(StatisticEvents.CATEGORY2_ENTRY.toString(), ""));
        String[] catIds = req.getParameterMap().get("catId");
        int catId;
        if(catIds == null || catIds.length == 0) {
            logger.info("Category ID was not provided");
            logger.debug("Set ID: 0");
            catId = 0;
        } else {
            catId = Integer.parseInt(catIds[0]);
            logger.debug("Category id = " + catId);
            statisticsBean.addStatistics(new Statistics(StatisticEvents.CATEGORY2_CHOICE.toString(), catIds[0]));
        }
        List<PickerCommandCard> pickerCommandCards = showMainCategories(catId);
        StringBuilder allCards = new StringBuilder();
        for(PickerCommandCard card : pickerCommandCards) {
            allCards.append(renderCard(card));
        }
        renderPage(resp, allCards.toString());
    }

    private void renderPage(HttpServletResponse response, String wyniki) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/categoryPickerCommand.html");
        JtwigModel model = JtwigModel.newModel().with("wyniki", wyniki);
        template.render(model, response.getOutputStream());
    }

    private String renderCard(PickerCommandCard card) {
        String link = card.isLastCategory() ? "#" : "/webapp/categoryPickerCommand?catId=" +card.getCatId();
        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/resultForCategoryPickerCommand.html");
        JtwigModel model = JtwigModel.newModel()
                .with("childerCategoriesLink", link)
                .with("title", card.getCategoryName())
                .with("backgroundUrl", card.getBackgroundURL())
                .with("allegroLink", card.getAllegroLink());

        return template.render(model);
    }

    private List<PickerCommandCard> showMainCategories(int id) {
        String filePath = System.getProperty("java.io.tmpdir") + "/file.xml";
        List<PickerCommandCard> mainCategories = new ArrayList<>();
        List<CategoryPicture> picturesList = fileConfiguration.getPictureList();
        Map<Integer, List<AllegroCategory>> categoryTree = loader.loadCategoryTree(filePath);
        List<AllegroCategory> allCategories = loader.loadAllCategories(filePath);
        List<AllegroCategory> categories = categoryTree.get(id);

        if (categories != null) {
            logger.info("Categories list was not empty");
            for (AllegroCategory category : categories) {
                Optional<AllegroCategory> mainCategory = this.mainCategory.getMainCategory(category, allCategories);
                int mainCatId = mainCategory.isPresent() ? mainCategory.get().getCatID() : 0;
                logger.debug("MainCategoryId = " + mainCatId);
                int catID = category.getCatID();
                logger.debug("Category ID = " +catID);
                String name = category.getName();
                logger.debug("Category name = " +name);
                boolean lastCategory = !categoryTree.containsKey(catID);

                PickerCommandCard card = new PickerCommandCard(lastCategory, catID, name,
                        imUrl.getImageUrl(picturesList, mainCatId),
                        AllegroLink.makeLink(name, catID));
                mainCategories.add(card);
            }
        }
        return mainCategories;
    }
}