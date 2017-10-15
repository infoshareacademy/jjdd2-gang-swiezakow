package pl.infoshareacademy.webapp.auth;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("fblogin")
public class FBAuthServlet extends HttpServlet {
    private static final String FACEBOOK_APP_ID = "145040069442337";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        
        String userName = req.getParameter("user_name");
        String userEmail = req.getParameter("user_email");

        if (userName == null || userEmail == null) {
            //wyswietl przycisk logowania
            JtwigTemplate template = JtwigTemplate.classpathTemplate("html/fblogin.html");
            JtwigModel model = JtwigModel.newModel();
            model.with("facebookAppId", FACEBOOK_APP_ID);
            template.render(model, resp.getOutputStream());
        } else {
            //User zalogowany, zapsiz dane do sesji, idz do menu
            req.getSession().setAttribute("UserName", userName);
            req.getSession().setAttribute("UserEmail", userEmail);
            resp.sendRedirect("main");
        }
    }
}
