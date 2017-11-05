package pl.infoshareacademy.webapp;

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

import static pl.infoshareacademy.webapp.auth.FBAuthServlet.*;

@WebServlet("/main")
public class MainMenuServlet extends HttpServlet {

    @Inject
    private StatisticsBean statisticsBean;

    @Inject
    private DashboardService dashboardService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        statisticsBean.saveStatistics(new Statistics(StatisticEvents.MENU_ENTRY.toString(), ""));

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        DashboardItem dashboardItem = dashboardService.randomImageGenerator();
        DashboardItem dashboardItem2 = dashboardService.randomImageGenerator();
        DashboardItem dashboardItem3 = dashboardService.randomImageGenerator();
        DashboardItem dashboardItem4 = dashboardService.getMostPopularCategory();
        DashboardItem dashboardItem5 = dashboardService.getPromotedCategory();

        String userName = (String) req.getSession().getAttribute(USER_NAME);
        String userEmail = (String) req.getSession().getAttribute(USER_EMAIL);
        Boolean isFbUser = "fb".equals(req.getSession().getAttribute(USER_LOGIN_TYPE));

        if (userName != null && userEmail != null) {
            req.setAttribute("username", userName);
            req.setAttribute("isFbUser", isFbUser);
        }
        req.setAttribute("image", dashboardItem);
        req.setAttribute("image2", dashboardItem2);
        req.setAttribute("image3", dashboardItem3);
        req.setAttribute("image4", dashboardItem4);
        req.setAttribute("image5", dashboardItem5);
        Translator.fillRequestAttributes(req);
        req.getRequestDispatcher("mainMenu.jsp").forward(req, resp);
    }
}