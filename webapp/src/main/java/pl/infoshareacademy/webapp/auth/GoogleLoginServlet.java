package pl.infoshareacademy.webapp.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_EMAIL;
import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_LOGIN_TYPE;
import static pl.infoshareacademy.webapp.auth.FBAuthServlet.USER_NAME;

@WebServlet("googlelog")
public class GoogleLoginServlet extends HttpServlet {
    Logger logger = LogManager.getLogger(GoogleLoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterMap().get("id");
        if (ids == null || ids.length == 0) {
            logger.info("id was not provided");
            resp.sendRedirect("fblogin");
        } else {
            String id = ids[0];
            HttpTransport transport = new ApacheHttpTransport();
            JsonFactory jsonFactory = new JacksonFactory();

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList("372851215939-v2iponke1e57fj4bqagmqsvkkgeu2m9f.apps.googleusercontent.com"))
                    .build();

            GoogleIdToken idToken = null;
            try {
                idToken = verifier.verify(id);
            } catch (GeneralSecurityException e) {
                logger.error("cannot verify id", e);
                e.printStackTrace(resp.getWriter());
            }
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                String userId = payload.getSubject();
                logger.info("User ID: " + userId);

                String email = payload.getEmail();
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                logger.info("User e-mail: " + email);
                logger.info("User picture: " + pictureUrl);

                req.getSession().setAttribute(USER_NAME, name);
                req.getSession().setAttribute(USER_EMAIL, email);
                req.getSession().setAttribute(USER_LOGIN_TYPE, "google");
                resp.sendRedirect("main");
            } else {
                logger.info("Invalid ID token.");
                resp.getWriter().write("Invalid ID token.");
            }
        }
    }
}
