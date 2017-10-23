package pl.infoshareacademy.webapp.auth;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.Configuration;
import pl.infoshareacademy.ConfigurationLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("fblogin")
public class FBAuthServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FBAuthServlet.class);

    public static final String USER_NAME = "UserName";
    public static final String USER_EMAIL = "UserEmail";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        
        String userName = req.getParameter("user_name");
        String userEmail = req.getParameter("user_email");
        String accessToken = req.getParameter("access_token");
        String userId = req.getParameter("user_id");

        ConfigurationLoader.loadConfiguration();
        Configuration config = ConfigurationLoader.getConfiguration();
        String facebookAppId = config.getFacebookAppId();

        if (userName == null || userEmail == null) {
            //wyswietl przycisk logowania

            req.setAttribute("facebookAppId", facebookAppId);
            req.getRequestDispatcher("fblogin.jsp").forward(req, resp);
        } else {
            //weryfikacja accessToken
            String APP_SECRET = "e447a6099a07e4eb7cab1ad19ef6ae50";
            try {
                HttpResponse<JsonNode> response = Unirest.get("https://graph.facebook.com/debug_token")
                        .queryString("input_token", accessToken)
                        .queryString("access_token", facebookAppId + "|" + APP_SECRET)
                        .asJson();

                LOGGER.info(response.getStatus() + response.getBody().toString());
                String uid = response.getBody().getObject().getJSONObject("data").getString("user_id");
                if (uid.equals(userId)) {
                    //User zalogowany, zapisz dane do sesji, idz do menu
                    req.getSession().setAttribute(USER_NAME, userName);
                    req.getSession().setAttribute(USER_EMAIL, userEmail);
                    resp.sendRedirect("main");
                    return;
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }

            resp.sendRedirect("fblogin");

        }
    }
}
