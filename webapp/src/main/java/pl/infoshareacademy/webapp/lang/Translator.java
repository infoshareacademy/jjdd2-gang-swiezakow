package pl.infoshareacademy.webapp.lang;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.webapp.categoryPickerCommandWeb.CategoryPickerCommandServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import static java.util.Arrays.asList;

public final class Translator {
    private static final Logger LOGGER = LogManager.getLogger(CategoryPickerCommandServlet.class);

    private static final String BASE_NAME = "Translation";
    private final static Set<Locale> SUPPORTED_LOCALES = new HashSet<>(asList(Locale.ROOT, Locale.ENGLISH));

    public static void fillRequestAttributes(HttpServletRequest modifiedRequest, Locale locale) {
        LOGGER.info("Getting translations for locale {}", locale);
        Locale actualLocale = SUPPORTED_LOCALES.contains(locale) ? locale : Locale.ROOT;
        LOGGER.debug("Actual locale {}", locale);
        ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, actualLocale);
        LOGGER.debug("Actual keylist: {}", bundle.keySet());
        for (String key : bundle.keySet()) {
            modifiedRequest.setAttribute(key, bundle.getString(key));
        }
    }

    public static void fillRequestAttributes(HttpServletRequest modifiedRequest) {
        fillRequestAttributes(modifiedRequest, getLocale(modifiedRequest));
    }

    public static Locale getLocale(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        String localeString = req.getParameter("locale");
        if (session == null || localeString != null) {
            Locale locale = localeString.isEmpty() ? Locale.ROOT : Locale.forLanguageTag(localeString);
            session = req.getSession();
            session.setAttribute("Locale", locale);
            LOGGER.info("Chosen locale is now:" + locale);
            return locale;
        } else {
            Locale locale = (Locale)session.getAttribute("Locale");
            return locale == null ? Locale.ROOT : locale;
        }
    }
}
