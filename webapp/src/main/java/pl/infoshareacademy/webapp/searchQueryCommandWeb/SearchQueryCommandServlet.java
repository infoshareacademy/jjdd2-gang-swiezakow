package pl.infoshareacademy.webapp.searchQueryCommandWeb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroCategoryLoader;
import pl.infoshareacademy.AllegroCategorySearcher;
import pl.infoshareacademy.webapp.dao.StatisticsBean;
import pl.infoshareacademy.webapp.entities.Statistics;
import pl.infoshareacademy.webapp.promotedCategories.PromotedCategoriesService;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.CategoryPicture;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.FileConfiguration;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.ImageFileParser;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.ImageUrl;
import pl.infoshareacademy.webapp.searchCategoryCommandWeb.MainCategory;
import pl.infoshareacademy.webapp.statistics.StatisticEvents;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebServlet("/searchQueryCommand")
public class SearchQueryCommandServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(SearchQueryCommandServlet.class);

    private final AllegroCategoryLoader loader = new AllegroCategoryLoader();
    private final AllegroCategorySearcher searcher = new AllegroCategorySearcher();
    private final ImageFileParser fileParser = new ImageFileParser();
    private final FileConfiguration fileConfiguration = fileParser.loadingImageFile();
    private final MainCategory main = new MainCategory();
    private final ImageUrl imageUrl = new ImageUrl();

    @Inject
    private StatisticsBean statisticsBean;

    @Inject
    private PromotedCategoriesService promotedCategoriesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        statisticsBean.saveStatistics(new Statistics(StatisticEvents.CATEGORY4_ENTRY.toString(), ""));

        String[] terms = req.getParameterMap().get("searchTerm");
        if (terms == null || terms.length == 0) {
            logger.info("searchTerm was not provided");
            renderPage(resp, "", "");
        } else {
            String searchTerm = terms[0];
            logger.debug("searchTerm = " + searchTerm);
            statisticsBean.saveStatistics(new Statistics(StatisticEvents.CATEGORY4_SEARCH.toString(), searchTerm));

            List<QueryCard> results = findCategory(searchTerm);

            Collections.sort(results, (n1, n2) -> {
                if(n1.isPromoted() == n2.isPromoted()) {
                    return 0;
                } else if (n1.isPromoted()) {
                    return -1;
                } else {
                    return 1;
                }
            });

            StringBuilder allCards = new StringBuilder();
            for (QueryCard card : results) {
                allCards.append(renderCard(card));
            }
            renderPage(resp, searchTerm, allCards.toString());
        }
    }

    private void renderPage(HttpServletResponse response, String searchTerm, String wyniki) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/searchQueryCommand.html");
        JtwigModel model = JtwigModel.newModel()
                .with("search", searchTerm)
                .with("wyniki", wyniki);
        template.render(model, response.getOutputStream());
    }

    private String renderCard(QueryCard card) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/resultForQueryCommand.html");
        String title = card.getCategoryName();
        JtwigModel model = JtwigModel.newModel()
                .with("isPromoted", card.isPromoted())
                .with("title", title)
                .with("backgroundUrl", card.getBackgroundUrl())
                .with("phrase", card.getPhrase());

        return template.render(model);
    }

    private List<QueryCard> findCategory(String searchTerm) {
        String filePath = System.getProperty("java.io.tmpdir") + "/file.xml";
        List<AllegroCategory> allCategories = loader.loadAllCategories(filePath);
        List<AllegroCategory> results = searcher.searchCategory(searchTerm, allCategories);
        List<CategoryPicture> pictures = fileConfiguration.getPictureList();
        List<QueryCard> cards = new ArrayList<>();

        for(AllegroCategory result : results) {

            Optional<AllegroCategory> mainCategory = main.getMainCategory(result, allCategories);
            String phrase = getPhrase(result, allCategories);

            String imName, imUrl;
            if (mainCategory.isPresent()) {
                int catId = mainCategory.get().getCatID();
                imUrl = imageUrl.getImageUrl(pictures, catId);
                imName = mainCategory.get().getName();
            } else {
                logger.warn("main category was not found");
                imUrl = "";
                imName= "";
            }
            QueryCard card = new QueryCard(imUrl, imName, promotedCategoriesService.isCategoryPromoted(result.getCatID()), phrase);
            cards.add(card);
        }
        logger.debug("returned " + cards.size() + " cards");
        return cards;
    }

    private String getPhrase(AllegroCategory result, List<AllegroCategory> allegroCategories) {
        int parent = result.getParent();
        String phrase = "";
        for (AllegroCategory category : allegroCategories) {
            if(category.getCatID() == parent) {
                phrase = category.getName();
                break;
            }
        }
        return String.format("%s %s", phrase, result.getName()).toLowerCase();
    }
}
