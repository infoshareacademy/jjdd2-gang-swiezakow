import java.util.Collections;
import java.util.List;


//Klasa kategorii, zawiera liste podkategorii.
public class Category {
    private String name;
    private String linkUrl;
    private List<Category> subcategories;

    //konstruktor
    public Category(String namek, String linkUrlk, List<Category> subcategoriesk) {
        name = namek;
        linkUrl = linkUrlk;
        subcategories = subcategoriesk;
    }

    public String getName() {
        return name;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }
}
