package pl.infoshareacademy.webapp;


import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class MainMenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/main.html");
        JtwigModel model = JtwigModel.newModel();
        template.render(model, resp.getOutputStream());
    }

}
