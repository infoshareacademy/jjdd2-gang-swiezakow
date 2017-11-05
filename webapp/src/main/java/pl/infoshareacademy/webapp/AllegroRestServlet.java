package pl.infoshareacademy.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.webapp.auth.FBAuthServlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/allegro")
public class AllegroRestServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AllegroRestServlet.class);

    @Inject
    private AllegroCategoryService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isAdmin = (Boolean) req.getSession().getAttribute(FBAuthServlet.USER_TYPE);
        if(isAdmin == null || !isAdmin) {
            logger.info("access denied");
            resp.sendRedirect("unauthorized");
            return;
        }
        service.loadCategoriesFromRest();
        resp.sendRedirect("main");
    }
}