package pl.infoshareacademy.webapp.statistics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_TYPE;

@WebServlet("/stats")
public class StatisticsServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(StatisticsServlet.class);

    @Inject
    private StatisticService statisticService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isAdmin = (Boolean) req.getSession().getAttribute(USER_TYPE);

        if(isAdmin == null || !isAdmin) {
            logger.info("access denied");
            resp.sendRedirect("unauthorized");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writer().writeValueAsString(statisticService.getStatsForCategoryEntries());
        String json2 = objectMapper.writer().writeValueAsString(statisticService.getStatsForMostPopularCategories());
        String json3 = objectMapper.writer().writeValueAsString(statisticService.getStatsForMostPopularPhrase());
        String json4 = objectMapper.writer().writeValueAsString(statisticService.getEntriesPerDay());

        req.setAttribute("json", json);
        req.setAttribute("json2", json2);
        req.setAttribute("json3", json3);
        req.setAttribute("json4", json4);

        req.getRequestDispatcher("/charts.jsp").forward(req, resp);
    }
}