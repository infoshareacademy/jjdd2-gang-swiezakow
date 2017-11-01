package pl.infoshareacademy.webapp.categoryPickerCommandWeb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@WebServlet("/categoryPickerCommand")
public class CategoryPickerCommandServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(CategoryPickerCommandServlet.class);

    @Inject
    private StatisticsBean statisticsBean;

    @Inject
    private CategoryPickerCommandService categoryPickerCommandService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        statisticsBean.saveStatistics(new Statistics(StatisticEvents.CATEGORY2_ENTRY.toString(), ""));
        String[] catIds = req.getParameterMap().get("catId");
        int catId;
        if(catIds == null || catIds.length == 0) {
            logger.info("Category ID was not provided");
            logger.debug("Set ID: 0");
            catId = 0;
        } else {
            catId = Integer.parseInt(catIds[0]);
            logger.debug("Category id = " + catId);
            statisticsBean.saveStatistics(new Statistics(StatisticEvents.CATEGORY2_CHOICE.toString(), catIds[0]));
        }
        req.setAttribute("categoryNumber", "3");
        req.setAttribute("mainCategories", categoryPickerCommandService.getMainCategories(catId));
        req.getRequestDispatcher("categoryPickerCommand.jsp").forward(req, resp);
    }
}
