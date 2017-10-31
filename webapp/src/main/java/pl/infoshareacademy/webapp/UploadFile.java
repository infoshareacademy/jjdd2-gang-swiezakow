package pl.infoshareacademy.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.Catalog;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index")
@MultipartConfig
public class UploadFile extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UploadFile.class);

    @Inject
    private Catalog catalog;

    @Inject
    private AllegroCategoryService allegroCategoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<form action=\"index\" method=\"post\" enctype=\"multipart/form-data\">");
        writer.println("<p>Select the XML file with data: ");
        writer.println("<input type=\"file\" name=\"fileXML\"/>");
        writer.println("</p>");
        writer.println("<button type=\"submit\">Send</button>");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Part fileXML = req.getPart("file.xml");
            allegroCategoryService.saveAllegroCategoryFile(fileXML.getInputStream());
            catalog.updateCatalog(allegroCategoryService.getFilePath());
            String message = "" + "<div class=\"alert alert-success\" role=\"alert\">\n" +
                    "  Poprawnie załadowano kategorie!\n" +
                    "</div>";
            req.setAttribute("message", message);
            req.getRequestDispatcher("main.jsp").forward(req, resp);

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Could not read the file");
        } catch (ServletException e) {
            e.printStackTrace();
            logger.error("Something gone wrong, try again");
        }
    }
}