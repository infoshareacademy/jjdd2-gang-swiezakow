package pl.infoshareacademy.webapp;


import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
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

        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/main.html");
        JtwigModel model = JtwigModel.newModel();
        if (userName != null && userEmail != null) {
            model.with("userDetails", "<div id=\"userDetails\">Zalogowano jako " + userName + "(" + userEmail + ")</div>");
        }

        template.render(model, resp.getOutputStream());
    }
}