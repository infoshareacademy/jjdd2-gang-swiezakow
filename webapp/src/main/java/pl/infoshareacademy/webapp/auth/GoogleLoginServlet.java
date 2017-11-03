package pl.infoshareacademy.webapp.auth;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import pl.infoshareacademy.webapp.authorisation.AdminService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_EMAIL;
import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_IMG;
import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_LOGIN_TYPE;
import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_NAME;
import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_TYPE;

@WebServlet("googlelog")
public class GoogleLoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GoogleLoginServlet.class);

    @Inject
    private AdminService adminService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterMap().get("id");
        if (ids == null || ids.length == 0) {
            logger.info("id was not provided");
            resp.sendRedirect("fblogin");
            return;
        } else {
            String id = ids[0];
            try {
                HttpResponse<JsonNode> id_token = Unirest.get("https://www.googleapis.com/oauth2/v3/tokeninfo")
                        .queryString("id_token", id)
                        .asJson();
                if (id_token.getStatus() >= 300) {
                    resp.getWriter().write("Invalid token, returned status was " + id_token.getStatus());
                } else {
                    JSONObject object = id_token.getBody().getObject();
                    String name = object.getString("name");
                    String email = object.getString("email");
                    String picture = object.getString("picture");

                    logger.info("User e-mail: " + email);
                    logger.info("User picture: " + picture);

                    req.getSession().setAttribute(USER_NAME, name);
                    req.getSession().setAttribute(USER_EMAIL, email);
                    req.getSession().setAttribute(USER_LOGIN_TYPE, "google");
                    req.getSession().setAttribute(USER_IMG, picture);
                    req.getSession().setAttribute(USER_TYPE, adminService.isAdmin(email));

                    resp.sendRedirect("main");
                }
            } catch (UnirestException e) {
                logger.error("Cannot validate google token", e);
                e.printStackTrace(resp.getWriter());
            }
        }
    }
}
