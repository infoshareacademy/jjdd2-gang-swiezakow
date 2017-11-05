package pl.infoshareacademy.webapp.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.reportService.UpdateRESTTimerThread;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteTaskServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DeleteTaskServlet.class);

    @Inject
    private ReportServlet reportServlet;

    @Inject
    UpdateRESTTimerThread updateRESTTimerThread;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("deletetasknumber");
        try {
            Integer deletedCategoryId = Integer.parseInt(input);
            logger.info("Task id to delete: " + deletedCategoryId);
            updateRESTTimerThread.deleteTask(deletedCategoryId);
        } catch (NumberFormatException e) {
            logger.error("could not parse input" + e);
        }
//        finally {
////            resp.sendRedirect("report");
//            req.getRequestDispatcher("report").forward(req, resp);
//        }
//        resp.setStatus(301);
        resp.sendRedirect("report");
//        reportServlet.doGet(req, resp);
    }
}
