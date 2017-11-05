package pl.infoshareacademy.webapp;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/allegro")
public class AllegroRestServlet extends HttpServlet {

    @Inject
    private AllegroCategoryService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.loadCategoriesFromRest();
        resp.sendRedirect("main");
    }
}