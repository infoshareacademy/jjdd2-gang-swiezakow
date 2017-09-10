import java.util.Collections;
import java.util.List;


//Klasa kategorii, zawiera liste podkategorii.
public class Category {
    private String name;
    private String linkUrl;
    private List<Category> subcategories;

    //konstruktor
    public Category(String name, String linkUrl, List<Category> subcategories) {
        this.name = name;
        this.linkUrl = linkUrl;
        this.subcategories = subcategories;
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
