package pl.infoshareacademy.webapp.searchCategoryCommandWeb;

import com.google.common.base.Joiner;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroCategoryLoader;
import pl.infoshareacademy.AllegroCategorySearcher;
import pl.infoshareacademy.SearchCategoryCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/searchCategoryCommand")
public class SearchCategoryCommandServlet extends HttpServlet {
    private final AllegroCategoryLoader loader = new AllegroCategoryLoader();
    private final AllegroCategorySearcher searcher = new AllegroCategorySearcher();
    private final ImageFileParser parser = new ImageFileParser();
    private final FileConfiguration fileConfiguration = parser.loadingImageFile();
    private final SearchCategoryCommand categoryCommand = new SearchCategoryCommand("");
    private final MainCategory main = new MainCategory();
    private final ImageUrl imUrl = new ImageUrl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] terms = req.getParameterMap().get("searchTerm");
        if (terms == null || terms.length == 0) {
            renderPage(resp, "", "");
        } else {
            String searchTerm = terms[0];
            List<Card> results = findCategories(searchTerm);
            StringBuilder allCards = new StringBuilder();
            for (Card result : results) {
                allCards.append(renderCard(result, searchTerm));
            }

            renderPage(resp, searchTerm, allCards.toString());
        }
    }

    private void renderPage(HttpServletResponse resp, String searchTerm, String wyniki) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/searchCategoryCommand.html");
        JtwigModel model = JtwigModel.newModel()
                .with("search", searchTerm)
                .with("wyniki", wyniki);
        template.render(model, resp.getOutputStream());
    }

    private List<Card> findCategories(String searchTerm) {
        String filePath = System.getProperty("java.io.tmpdir") + "/file.xml";
        List<AllegroCategory> allegroCategories = loader.loadAllCategories(filePath);
        List<AllegroCategory> categoryResults = searcher.searchCategory(searchTerm, allegroCategories);
        List<CategoryPicture> picturesList = fileConfiguration.getPictureList();
        List<Card> cards = new ArrayList<Card>();

        for (AllegroCategory categoryResult : categoryResults) {

            Optional<AllegroCategory> mainCategory = main.getMainCategory(categoryResult, allegroCategories);
            List<AllegroCategory> allParents = getAllParentsCategory(categoryResult, allegroCategories);

            String imageUrl;
            if (mainCategory.isPresent()) {
                int catID = mainCategory.get().getCatID();
                imageUrl = imUrl.getImageUrl(picturesList, catID);
            } else {
                imageUrl = "";
            }

            Card card = new Card(allParents, imageUrl);
            cards.add(card);
        }
        return cards;
    }

    private List<AllegroCategory> getAllParentsCategory(AllegroCategory categoryResult, List<AllegroCategory> list){
        int parent = categoryResult.getParent();
        List<AllegroCategory> allParentCategory = new ArrayList<AllegroCategory>();
        allParentCategory.add(categoryResult);
        while(parent != 0) {
            for (AllegroCategory allCategory : list) {
                if (allCategory.getCatID() == parent) {
                    allParentCategory.add(allCategory);
                    parent = allCategory.getParent();
                    break;
                }
            }
        }
        return allParentCategory;
    }

    private String renderCard(Card card, String searchTerm) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/result.html");
        String title = getParentLinks(card.getAllParents(), searchTerm);
        JtwigModel model = JtwigModel.newModel()
                .with("title", title)
                .with("backgroundUrl", card.getBackgroundUrl());
        return template.render(model);
    }

    private String getParentLinks(List<AllegroCategory> parents, String searchTerm) {
        List<String> list = new ArrayList<String>();
        for (int i = parents.size() - 1; i >= 0; i--) {
            String url = categoryCommand.generateLink(parents.get(i), searchTerm);
            String aLink = String.format("<a style=\"color: white; text-shadow: 2px 2px 2px #333333;\" href=\"%s\">%s</a>",
                    url, parents.get(i).toString().toUpperCase());
            list.add(aLink);
        }
        return Joiner.on(" - ").join(list);
    }
}
