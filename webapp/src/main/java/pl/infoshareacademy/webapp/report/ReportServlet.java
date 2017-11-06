package pl.infoshareacademy.webapp.report;

import pl.infoshareacademy.reportService.ModelsStore.RecipientModel;
import pl.infoshareacademy.reportService.UpdateRESTTimerThread;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    @Inject
    UpdateRESTTimerThread updateRESTTimerThread;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (updateRESTTimerThread.getActualTasks().isPresent()){
        ArrayList<RecipientModel> actualTasks = updateRESTTimerThread.getActualTasks().get();
        String lastUpdateDate = updateRESTTimerThread.getLastUpdateDate();
        req.setAttribute("tasks", actualTasks);
        req.setAttribute("lastUpdateDate", lastUpdateDate);
        req.getRequestDispatcher("report.jsp").forward(req, resp);
        }

        PrintWriter pw = resp.getWriter();
        pw.println("<h1>Modul raportowy niedostepny.</h1>");
        pw.println("<h1>Prosimy sprobowac ponownie pozniej</h1>");
        pw.println("<h1>Za utrudnienia przepraszamy</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String year = req.getParameter("year");
        String month = req.getParameter("month");
        String day = req.getParameter("day");
        String hour = req.getParameter("hour");
        String minutes = req.getParameter("minutes");
        Integer interval = Integer.valueOf(req.getParameter("interval"));

        StringBuilder date = new StringBuilder(year + "-" + month + "-" + day + " " + hour+ ":" + minutes);
        List<String> attachment = new ArrayList<>();
        attachment.add(email);
        updateRESTTimerThread.addTaskInReportModule(new RecipientModel(attachment, date.toString(), interval, 0));
        doGet(req, resp);
//        req.getRequestDispatcher("report.jsp").forward(req, resp);
    }
}
