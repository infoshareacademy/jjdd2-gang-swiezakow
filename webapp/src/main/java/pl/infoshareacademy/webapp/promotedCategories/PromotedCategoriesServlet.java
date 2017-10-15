package pl.infoshareacademy.webapp.promotedCategories;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

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
    PromotedCategoriesService categoriesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

        JtwigTemplate template = JtwigTemplate.classpathTemplate("html/promoted/promotedCategories.html");
        PromotedCategoriesData promotedCategory = categoriesService.getPromotedCategoriesData(catId);
        JtwigModel model = JtwigModel.newModel()
                .with("categories", promotedCategory.getCategoriesForParent())
                .with("actualId", catId)
                .with("selectedIds", promotedCategory.getPromotedCategoriesIds());

        template.render(model, resp.getOutputStream());
    }
}
