package pl.infoshareacademy.webapp.searchQueryCommandWeb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.webapp.dao.StatisticsBean;
import pl.infoshareacademy.webapp.entities.Statistics;
import pl.infoshareacademy.webapp.lang.Translator;
import pl.infoshareacademy.webapp.statistics.StatisticEvents;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/searchQueryCommand")
public class SearchQueryCommandServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(SearchQueryCommandServlet.class);

    @Inject
    private StatisticsBean statisticsBean;

    @Inject
    private SearchQueryService queryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        statisticsBean.saveStatistics(new Statistics(StatisticEvents.CATEGORY4_ENTRY.toString(), ""));

        String[] terms = req.getParameterMap().get("searchTerm");
        if (terms == null || terms.length == 0) {
            req.setAttribute("searchTerm", "");
            req.setAttribute("queryCard", Collections.EMPTY_LIST);
            logger.info("searchTerm was not provided");
        } else {
            String searchTerm = terms[0];
            logger.debug("searchTerm = " + searchTerm);
            List<QueryCard> results = queryService.getQueryCards(searchTerm);
            statisticsBean.saveStatistics(new Statistics(StatisticEvents.CATEGORY4_SEARCH.toString(), searchTerm));

            req.setAttribute("searchTerm", searchTerm);
            req.setAttribute("queryCard", results);
        }
        req.setAttribute("categoryNumber", "4");
        Translator.fillRequestAttributes(req);
        req.getRequestDispatcher("searchQueryCommand.jsp").forward(req, resp);
    }
}