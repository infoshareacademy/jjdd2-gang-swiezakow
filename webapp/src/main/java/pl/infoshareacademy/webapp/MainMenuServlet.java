package pl.infoshareacademy.webapp;

import pl.infoshareacademy.webapp.auth.FBAuthServlet;
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

import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_EMAIL;
import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_IMG;
import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_LOGIN_TYPE;
import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_NAME;

@WebServlet("/main")
public class MainMenuServlet extends HttpServlet {

    @Inject
    private StatisticsBean statisticsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        statisticsBean.saveStatistics(new Statistics(StatisticEvents.MENU_ENTRY.toString(), ""));

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String userName = (String) req.getSession().getAttribute(USER_NAME);
        String userEmail = (String) req.getSession().getAttribute(USER_EMAIL);
        Boolean isFbUser = "fb".equals(req.getSession().getAttribute(USER_LOGIN_TYPE));


        if (userName != null && userEmail != null) {
            req.setAttribute("username", userName);
            req.setAttribute("isFbUser", isFbUser);
        }
        req.getRequestDispatcher("proba.jsp").forward(req, resp);
    }
}