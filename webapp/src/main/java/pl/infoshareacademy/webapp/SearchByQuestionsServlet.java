package pl.infoshareacademy.webapp;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import pl.infoshareacademy.AllegroLink;
import pl.infoshareacademy.SearchByQuestions;
import pl.infoshareacademy.SearchResult;
import pl.infoshareacademy.webapp.dao.StatisticsBean;
import pl.infoshareacademy.webapp.entities.Statistics;
import pl.infoshareacademy.webapp.statistics.StatisticEvents;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@WebServlet("SearchByQuestions")
public class SearchByQuestionsServlet extends HttpServlet {

    @Inject
    private SearchByQuestions searchByQuestions;

    @Inject
    private StatisticsBean statisticsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String theAnswer = req.getParameter("theAnswer");
        String categoryStringId = req.getParameter("categoryId");

        if (categoryStringId == null || theAnswer == null) {
            statisticsBean.addStatistics(new Statistics(StatisticEvents.CATEGORY1_ENTRY.toString(), ""));
            categoryStringId = "0";
            theAnswer = "Tak";

        }

        boolean isChosen = "Tak".equals(theAnswer);
        int categoryId = Integer.parseInt(categoryStringId);

        SearchResult searchResult = null;
        boolean isResultPresent = true;
        if (isChosen) {
            searchResult = searchByQuestions.chooseCategory(categoryId);
        } else {
            Optional<SearchResult> optionalOmitResult = searchByQuestions.omitCategory(categoryId);
            if (!optionalOmitResult.isPresent()) {
                isResultPresent = false;
            } else {
                searchResult = optionalOmitResult.get();
            }
        }

        String output;

        if (!isResultPresent) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("html/SearchByQuestions/searchByQuestionsNoCategory.html");
            JtwigModel model = JtwigModel.newModel();
            output = template.render(model);
        } else {
            String foundCategoryName = searchResult.getCategoryName();
            int foundCategoryId = searchResult.getCategoryId();
            boolean isLink = searchResult.isLink();

            if (isLink) {
                statisticsBean.addStatistics(new Statistics(StatisticEvents.CATEGORY1_CHOICE.toString(), ""+foundCategoryId));
                String link = AllegroLink.makeLink(foundCategoryName, foundCategoryId);
                JtwigTemplate template = JtwigTemplate.classpathTemplate("html/SearchByQuestions/searchByQuestionsLink.html");
                JtwigModel model = JtwigModel.newModel()
                        .with("link", link);
                output = template.render(model);
            } else {
                JtwigTemplate template = JtwigTemplate.classpathTemplate("html/SearchByQuestions/searchByQuestionsForm.html");
                JtwigModel model = JtwigModel.newModel()
                        .with("categoryName", foundCategoryName)
                        .with("categoryId", foundCategoryId);
                output = template.render(model);
            }
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/SearchByQuestions/searchByQuestions.html");
        JtwigModel model = JtwigModel.newModel()
                .with("output", output);
        template.render(model, resp.getOutputStream());
    }
}
