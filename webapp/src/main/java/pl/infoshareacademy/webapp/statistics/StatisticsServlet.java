package pl.infoshareacademy.webapp.statistics;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroCategoryLoader;
import pl.infoshareacademy.webapp.dao.StatisticResult;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/stats")
public class StatisticsServlet extends HttpServlet {

    @Inject
    private StatisticsResultsBean resultsBean;

    private static final AllegroCategoryLoader loader = new AllegroCategoryLoader();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = System.getProperty("java.io.tmpdir") + "/file.xml";
        List<AllegroCategory> allCategories = loader.loadAllCategories(filePath);

        resp.addHeader("Content-Type", "text/html; charset=UTF-8");

        resp.getWriter().write("<html><body>");

        resp.getWriter().write("<h2>TOP 5 najpopularniejszych kategorii:</h2>");
        List<StatisticResult> mostPopularCategories = resultsBean.getMostPopularCategories();
        List<StatisticResult> prettyMostPopularCategories = idNameToCategoryName(mostPopularCategories, allCategories);
        resp.getWriter().write(getPhrase(prettyMostPopularCategories));

        resp.getWriter().write("<h2>Licznik wejść do poszczególnych kategorii:</h2>");
        List<StatisticResult> numberOfCategoryEntries = resultsBean.getNumberOfCategoryEntries();
        resp.getWriter().write(getPhrase(numberOfCategoryEntries));

        resp.getWriter().write("<h2>Licznik odwiedzin strony:</h2>");
        List<StatisticResult> numberOfVisistors = resultsBean.getNumberOfVisistors();
        resp.getWriter().write(getPhrase(numberOfVisistors));

        resp.getWriter().write("</body></html>");
    }

    private List<StatisticResult> idNameToCategoryName(List<StatisticResult> list, List<AllegroCategory> allegroCategories) {
        ArrayList<StatisticResult> statisticResults = new ArrayList<>();
        for (StatisticResult statisticResult : list) {
            String name = categoryName(Integer.parseInt(statisticResult.getName()), allegroCategories);
            statisticResults.add(new StatisticResult(name, statisticResult.getNumber()));
        }
        return statisticResults;
    }

    private String renderPage(StatisticResult statisticResult ) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/statistics.html");
        JtwigModel model = new JtwigModel()
                .with("name", statisticResult.getName())
                .with("number", statisticResult.getNumber());
        return template.render(model);
    }

    private String getPhrase(List<StatisticResult> list) throws IOException {
        String result = "";
        for (StatisticResult statisticResult : list) {
             result = result.concat(renderPage(statisticResult));
        }
        return result;
    }

    private String categoryName(int id, List<AllegroCategory> allCategories) {
        String categoryName = "";
        for (AllegroCategory allCategory : allCategories) {
            if(allCategory.getCatID() == id) {
                categoryName = allCategory.getName();
            }
        }
        return categoryName;
    }
}
