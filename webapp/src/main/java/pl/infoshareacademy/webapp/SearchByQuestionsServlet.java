package pl.infoshareacademy.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.Optional;

@WebServlet("SearchByQuestions")
public class SearchByQuestionsServlet extends HttpServlet {

    private final static Logger logger = LogManager.getLogger(SearchByQuestionsServlet.class);

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
            statisticsBean.saveStatistics(new Statistics(StatisticEvents.CATEGORY1_ENTRY.toString(), ""));
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

        req.setAttribute("isResultNotPresent", !isResultPresent);

        if (isResultPresent) {
            String foundCategoryName = searchResult.getCategoryName();
            int foundCategoryId = searchResult.getCategoryId();
            boolean isLink = searchResult.isLink();
            logger.info("Is link: " + isLink);
            req.setAttribute("isLink", isLink);
            req.setAttribute("categoryName", foundCategoryName);
            req.setAttribute("categoryId", foundCategoryId);
            if (isLink) {
                statisticsBean.saveStatistics(new Statistics(StatisticEvents.CATEGORY1_CHOICE.toString(), "" + foundCategoryId));
                String link = AllegroLink.makeLink(foundCategoryName, foundCategoryId);
                req.setAttribute("link", link);
            }
        }
        req.setAttribute("categoryNumber", "1");
        req.getRequestDispatcher("searchByQuestions.jsp").forward(req, resp);
    }
}