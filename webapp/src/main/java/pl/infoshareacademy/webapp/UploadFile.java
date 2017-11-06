package pl.infoshareacademy.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.Catalog;
import pl.infoshareacademy.webapp.auth.FBAuthServlet;
import pl.infoshareacademy.webapp.lang.Translator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

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
        Boolean isAdmin = (Boolean) req.getSession().getAttribute(FBAuthServlet.USER_TYPE);
        if(isAdmin == null || !isAdmin) {
            logger.info("access denied");
            resp.sendRedirect("unauthorized");
            return;
        }
       req.getRequestDispatcher("uploadFile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Boolean isAdmin = (Boolean) req.getSession().getAttribute(FBAuthServlet.USER_TYPE);
        if(isAdmin == null || !isAdmin) {
            logger.info("access denied");
            resp.sendRedirect("unauthorized");
            return;
        }
        try {
            Part fileXML = req.getPart("fileXML");
            Translator.fillRequestAttributes(req);
            allegroCategoryService.saveAllegroCategoryFile(fileXML.getInputStream());
            catalog.updateCatalog(allegroCategoryService.getFilePath());
            resp.sendRedirect("main");

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Could not read the file");
        } catch (ServletException e) {
            e.printStackTrace();
            logger.error("Something gone wrong, try again");
        }
    }
}