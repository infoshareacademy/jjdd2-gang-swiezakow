package pl.infoshareacademy.webapp.auth;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import pl.infoshareacademy.Configuration;
import pl.infoshareacademy.ConfigurationLoader;
import pl.infoshareacademy.webapp.authorisation.AdminService;

import javax.inject.Inject;
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
    public static final String USER_LOGIN_TYPE = "UserLoginType";
    public static final String USER_IMG = "UserUrl";
    public static final String USER_TYPE = "userType";

    @Inject
    private AdminService adminService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        ConfigurationLoader.loadConfiguration();
        Configuration config = ConfigurationLoader.getConfiguration();
        String facebookAppId = config.isUseTestFB() ? config.getTestFacebookAppId() : config.getFacebookAppId();
        String facebookAppSecret = config.isUseTestFB() ? config.getTestFacebookAppSecret() : config.getFacebookAppSecret();

        String logout = req.getParameter("logout");
        if ("1".equals(logout)) {
            String loginType = (String) req.getSession().getAttribute(USER_LOGIN_TYPE);
            req.getSession().removeAttribute(USER_NAME);
            req.getSession().removeAttribute(USER_EMAIL);
            req.getSession().removeAttribute(USER_LOGIN_TYPE);

            if ("fb".equals(loginType)) {
                req.setAttribute("facebookAppId", facebookAppId);
                req.getRequestDispatcher("fblogout.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("fblogin");
            }
            return;
        }
        
        String userName = req.getParameter("user_name");
        String userEmail = req.getParameter("user_email");
        String picture = req.getParameter("picture");
        String accessToken = req.getParameter("access_token");
        String userId = req.getParameter("user_id");

        if (userName == null || userEmail == null) {

            req.setAttribute("facebookAppId", facebookAppId);
            req.getRequestDispatcher("fblogin.jsp").forward(req, resp);
        } else {
            String APP_SECRET = facebookAppSecret;
            try {
                HttpResponse<JsonNode> response = Unirest.get("https://graph.facebook.com/debug_token")
                        .queryString("input_token", accessToken)
                        .queryString("access_token", facebookAppId + "|" + APP_SECRET)
                        .asJson();

                LOGGER.info(response.getStatus() + response.getBody().toString());
                String uid = response.getBody().getObject().getJSONObject("data").getString("user_id");
                if (uid.equals(userId)) {
                    req.getSession().setAttribute(USER_NAME, userName);
                    req.getSession().setAttribute(USER_EMAIL, userEmail);
                    req.getSession().setAttribute(USER_IMG, picture);
                    LOGGER.info("e-mail: " + userEmail);
                    req.getSession().setAttribute(USER_LOGIN_TYPE, "fb");
                    req.getSession().setAttribute(USER_TYPE, adminService.isAdmin(userEmail));
                    resp.sendRedirect("main");
                    return;
                }
            } catch (UnirestException | JSONException e) {
                LOGGER.error("Could not confirm token. " + e.getMessage(), e);
                req.setAttribute("message", "Could not authenticate with FB. Please try again or use alternative log in.");
            } finally {
                req.getRequestDispatcher("fblogin.jsp").forward(req, resp);
            }
        }
    }
}