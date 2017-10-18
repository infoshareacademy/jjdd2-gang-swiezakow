package pl.infoshareacademy.webapp.statistics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroCategoryLoader;
import pl.infoshareacademy.webapp.dao.StatisticResult;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;
import pl.infoshareacademy.webapp.raports.DataSets;
import pl.infoshareacademy.webapp.raports.Report;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/stats")
public class StatisticsServlet extends HttpServlet {

    private static final List<String> colors = Arrays.asList("#A7FFEB", "#FF8A80", "#FFE57F", "#B388FF");

    @Inject
    private StatisticsResultsBean resultsBean;

    private static final AllegroCategoryLoader loader = new AllegroCategoryLoader();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = System.getProperty("java.io.tmpdir") + "/file.xml";
        List<AllegroCategory> allCategories = loader.loadAllCategories(filePath);

        List<StatisticResult> mostPopularCategories = resultsBean.getMostPopularCategories();

        List<StatisticResult> prettyMostPopularCategories = idNameToCategoryName(mostPopularCategories, allCategories);

        List<StatisticResult> numberOfCategoryEntries = resultsBean.getNumberOfCategoryEntries();

        List<StatisticResult> numberOfVisistors = resultsBean.getNumberOfVisistors();

        DataSets dataSets = new DataSets(colors, numberOfCategoryEntries.stream()
                .map(StatisticResult::getNumber)
                .collect(Collectors.toList()));
        List<String> label = numberOfCategoryEntries.stream()
                .map(StatisticResult::getName)
                .collect(Collectors.toList());

        Report report = new Report(Arrays.asList(dataSets), label);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writer().writeValueAsString(report);

        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/reports/charts.html");
        JtwigModel model = JtwigModel.newModel().with("json", json);

        template.render(model, resp.getOutputStream());
    }

    private List<StatisticResult> idNameToCategoryName(List<StatisticResult> list, List<AllegroCategory> allegroCategories) {
        ArrayList<StatisticResult> statisticResults = new ArrayList<>();
        for (StatisticResult statisticResult : list) {
            String name = categoryName(Integer.parseInt(statisticResult.getName()), allegroCategories);
            statisticResults.add(new StatisticResult(name, statisticResult.getNumber()));
        }
        return statisticResults;
    }

    private String categoryName(int id, List<AllegroCategory> allCategories) {
        String categoryName = "";
        for (AllegroCategory allCategory : allCategories) {
            if (allCategory.getCatID() == id) {
                categoryName = allCategory.getName();
            }
        }
        return categoryName;
    }
}
