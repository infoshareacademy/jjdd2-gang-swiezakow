package pl.infoshareacademy.webapp;

import pl.infoshareacademy.reportService.ModelsStore.RecipientModel;
import pl.infoshareacademy.reportService.UpdateRESTTimerThread;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    @Inject
    UpdateRESTTimerThread updateRESTTimerThread;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<RecipientModel> actualTasks = updateRESTTimerThread.getActualTasks();
        req.setAttribute("tasks", actualTasks);
        req.getRequestDispatcher("report.jsp").forward(req, resp);
    }
}
