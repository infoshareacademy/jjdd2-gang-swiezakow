import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryUtils {
    public static Category buildCategoriesAndReturnRoot() {
        List<Category> level1Categories = new ArrayList<>();
        level1Categories.add(new Category("Elektronika", "{url/elektronika}", Collections.emptyList()));
        level1Categories.add(new Category("Czesci samochodowe", "{url/czesci}", Collections.emptyList()));
        level1Categories.add(new Category("Ksiazki", "{url/ksiazki}", Collections.emptyList()));
        Category root = new Category("ROOT", "N/A", level1Categories);
        return root;
    }

}
