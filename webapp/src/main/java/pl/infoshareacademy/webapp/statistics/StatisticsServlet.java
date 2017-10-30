package pl.infoshareacademy.webapp.statistics;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Inject
    private StatisticService statisticService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writer().writeValueAsString(statisticService.getStatsForCategoryEntries());
        String json2 = objectMapper.writer().writeValueAsString(statisticService.getStatsForMostPopularCategories());
        String json3 = objectMapper.writer().writeValueAsString(statisticService.getStatsForMostPopularPhrase());
        String json4 = objectMapper.writer().writeValueAsString(statisticService.getEntriesPerDay());
        String json5 = objectMapper.writer().writeValueAsString(statisticService.getStatsForMostPopularLink());


        req.setAttribute("json", json);
        req.setAttribute("json2", json2);
        req.setAttribute("json3", json3);
        req.setAttribute("json4", json4);
        req.setAttribute("json5", json5);

        req.getRequestDispatcher("/charts.jsp").forward(req, resp);
    }
}