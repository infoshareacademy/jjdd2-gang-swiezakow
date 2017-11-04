package pl.infoshareacademy.webapp.promotedCategories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.webapp.auth.FBAuthServlet;

import pl.infoshareacademy.webapp.lang.Translator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/promoted")
public class PromotedCategoriesServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PromotedCategoriesServlet.class);

    @Inject
    private PromotedCategoriesService categoriesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Boolean isAdmin = (Boolean) req.getSession().getAttribute(FBAuthServlet.USER_TYPE);
        if(isAdmin == null || !isAdmin) {
            logger.info("access denied");
            resp.sendRedirect("unauthorized");
            return;
        }

        Map<String, String[]> parameterMap = req.getParameterMap();

        String[] strings = parameterMap.get("parentid");
        String[] ids = parameterMap.get("id");
        String[] saves = parameterMap.get("save");

        String id = strings != null && strings.length > 0 ? strings[0] : "0";

        Integer catId = Integer.parseInt(id);

        if (saves != null && saves.length > 0) {
            ids = ids != null ? ids : new String[]{};
            List<Integer> integerList = Arrays.stream(ids)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            categoriesService.savePromotedCategories(catId, integerList);
        }

        PromotedCategoriesData promotedCategory = categoriesService.getPromotedCategoriesData(catId);

        req.setAttribute("categories", promotedCategory.getCategoriesForParent());
        req.setAttribute("actualId", catId);
        req.setAttribute("selectedIds", promotedCategory.getPromotedCategoriesIds());
        Translator.fillRequestAttributes(req);
        req.getRequestDispatcher("promotedCategories.jsp").forward(req, resp);
    }
}