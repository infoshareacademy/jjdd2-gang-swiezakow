package excercise;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<Category> subcategories;

    public Category(int cid, String cname, List<Category> csubcategories) {
        this.id = cid;
        this.name = cname;
        this.subcategories = csubcategories;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }
}