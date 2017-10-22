package pl.infoshareacademy.webapp.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("fblogin")
public class FBAuthServlet extends HttpServlet {
    private static final String FACEBOOK_APP_ID = "145040069442337";
    public static final String USER_NAME = "UserName";
    public static final String USER_EMAIL = "UserEmail";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        
        String userName = req.getParameter("user_name");
        String userEmail = req.getParameter("user_email");

        if (userName == null || userEmail == null) {
            //wyswietl przycisk logowania
            req.setAttribute("facebookAppId", FACEBOOK_APP_ID);
            req.getRequestDispatcher("fblogin.jsp").forward(req, resp);
        } else {
            //User zalogowany, zapisz dane do sesji, idz do menu
            req.getSession().setAttribute(USER_NAME, userName);
            req.getSession().setAttribute(USER_EMAIL, userEmail);
            resp.sendRedirect("main");
        }
    }
}
