package pl.infoshareacademy.webapp.statistics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import pl.infoshareacademy.AllegroCategoryLoader;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stats")
public class StatisticsServlet extends HttpServlet {

    private static final AllegroCategoryLoader loader = new AllegroCategoryLoader();

    @Inject
    private StatisticsResultsBean resultsBean;

    @Inject
    private StatisticService statisticService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writer().writeValueAsString(statisticService.getStatsForCategoryEntries());
        String json2 = objectMapper.writer().writeValueAsString(statisticService.getStatsForMostPopularCategories());
        String json3 = objectMapper.writer().writeValueAsString(statisticService.getStatsForMostPopularPhrase());
        String json4 = objectMapper.writer().writeValueAsString(statisticService.getEntriesPerDay());

        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/reports/charts.html");
        JtwigModel model = JtwigModel.newModel()
                .with("json", json)
                .with("json2", json2)
                .with("json3", json3)
                .with("json4", json4);

        template.render(model, resp.getOutputStream());
    }
}
