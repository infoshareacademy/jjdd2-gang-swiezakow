package pl.infoshareacademy.webapp;

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

@WebServlet("/main")
public class MainMenuServlet extends HttpServlet {

    @Inject
    private StatisticsBean statisticsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        statisticsBean.saveStatistics(new Statistics(StatisticEvents.MENU_ENTRY.toString(), ""));

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String userName = (String) req.getSession().getAttribute("UserName");
        String userEmail = (String) req.getSession().getAttribute("UserEmail");


        if (userName != null && userEmail != null) {
            String userDetails = "<div id=\"userDetails\">Zalogowano jako " + userName + " (<a href=\"fblogin?logout=1\">Wyloguj</a>)</div>";
            req.setAttribute("userDetails", userDetails);
        }

        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}