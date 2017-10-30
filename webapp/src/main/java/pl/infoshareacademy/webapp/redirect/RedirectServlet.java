package pl.infoshareacademy.webapp.redirect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.AllegroLink;
import pl.infoshareacademy.SearchCategoryCommand;
import pl.infoshareacademy.webapp.AllegroCategoryService;
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

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RedirectServlet.class);

    @Inject
    private StatisticsBean statisticsBean;

    @Inject
    private AllegroCategoryService service;

    private SearchCategoryCommand searchCategoryCommand = new SearchCategoryCommand(null);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterMap().get("id");
        String[] phrases = req.getParameterMap().get("phrase");
        String[] menuIds = req.getParameterMap().get("menuId");

        if (ids == null || ids.length == 0 || menuIds == null || menuIds.length == 0) {
            logger.info("invalid parameters provided");
            resp.sendRedirect("main");
            return;
        }
        int id = Integer.parseInt(ids[0]);

        saveStatisticsForAllegroLink(menuIds, id);
        resp.sendRedirect(getAllegroLink(id, phrases));
    }

    private String getAllegroLink(int id, String[] phrases) {
        if (phrases == null || phrases.length == 0) {
            return AllegroLink.makeLink(service.getCategoryName(id), id);
        }
        return searchCategoryCommand.generateLink(service.getParentForCatId(id), phrases[0]);
    }

    private void saveStatisticsForAllegroLink(String[] menuIds, int id) {
        String name;
        switch (menuIds[0]) {
            case ("0"):
                name = StatisticEvents.MAIN_LINK.toString();
                break;
            case ("1"):
                name = StatisticEvents.CATEGORY1_LINK.toString();
                break;
            case ("2"):
                name = StatisticEvents.CATEGORY2_LINK.toString();
                break;
            case ("3"):
                name = StatisticEvents.CATEGORY3_LINK.toString();
                break;
            default:
                return;
        }
        statisticsBean.saveStatistics(new Statistics(name, service.getCategoryName(id)));
    }
}
