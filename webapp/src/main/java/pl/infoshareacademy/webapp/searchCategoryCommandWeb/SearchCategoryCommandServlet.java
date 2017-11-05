package pl.infoshareacademy.webapp.searchCategoryCommandWeb;

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

@WebServlet("/searchCategoryCommand")
public class SearchCategoryCommandServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(SearchCategoryCommandServlet.class);

    @Inject
    private StatisticsBean statisticsBean;

    @Inject
    private SearchCategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        statisticsBean.saveStatistics(new Statistics(StatisticEvents.CATEGORY3_ENTRY.toString(), ""));

        String[] terms = req.getParameterMap().get("searchTerm");
        if (terms == null || terms.length == 0) {
            req.setAttribute("searchTerm", "");
            req.setAttribute("searchResults", Collections.EMPTY_LIST);
            logger.info("Category terms was not provided");
        } else {
            String searchTerm = terms[0];
            logger.info("SearchTerm = " + searchTerm);
            List<SearchResult> searchResults = categoryService.getSearchResults(searchTerm);
            statisticsBean.saveStatistics(new Statistics(StatisticEvents.CATEGORY3_SEARCH.toString(), searchTerm));

            req.setAttribute("searchTerm", searchTerm);
            req.setAttribute("searchResults", searchResults);
        }
        req.setAttribute("categoryNumber", "2");
        Translator.fillRequestAttributes(req);
        req.getRequestDispatcher("searchCategoryCommand.jsp").forward(req, resp);
    }
}