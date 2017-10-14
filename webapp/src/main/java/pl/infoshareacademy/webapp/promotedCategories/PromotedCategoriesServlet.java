package pl.infoshareacademy.webapp.promotedCategories;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.webapp.AllegroCategoryService;
import pl.infoshareacademy.webapp.dao.PromotedCategoriesBean;

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

    @Inject
    AllegroCategoryService service;

    @Inject
    PromotedCategoriesBean promotedCategoriesBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> parameterMap = req.getParameterMap();

        String[] strings = parameterMap.get("parentid");
        String[] ids = parameterMap.get("id");
        String[] saves = parameterMap.get("save");

        String id = strings != null && strings.length > 0 ? strings[0] : "0";
        Integer catId = Integer.parseInt(id);
        List<AllegroCategory> categoriesForParent = service.getAllegroCategoriesForParent(catId);

        if (saves != null && saves.length > 0) {

            ids = ids != null ? ids : new String[]{};
            List<Integer> deleteCategories = categoriesForParent.stream()
                    .map(AllegroCategory::getCatID)
                    .collect(Collectors.toList());

            promotedCategoriesBean.deletePromotedCategories(deleteCategories);

            List<Integer> integerList = Arrays.stream(ids)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            promotedCategoriesBean.savePromotedCategories(integerList);
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/promoted/promotedCategories.html");
        JtwigModel model = JtwigModel.newModel()
                .with("categories", categoriesForParent)
                .with("actualId", catId)
                .with("selectedIds", promotedCategoriesBean.getPromotedCategories());

        template.render(model, resp.getOutputStream());
    }
}
