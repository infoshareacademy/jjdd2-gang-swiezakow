package pl.infoshareacademy.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/notFound", "/unauthorized"})
public class ErrorServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ErrorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String servletPath = req.getServletPath();
        logger.info("Path: " + servletPath);
        String path = "mainMenu.jsp";

        switch (servletPath) {
            case "/notFound":
                path = "notFound.jsp";
                break;
            case "/unauthorized":
                path = "unauthorized.jsp";
                break;
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
