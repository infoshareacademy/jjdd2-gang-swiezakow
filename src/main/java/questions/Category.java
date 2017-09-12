package questions;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<Category> subcategories;
    private String question;

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

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question != null ? question : "Czy interesuje Cię kategoria " + name + "?";
    }
}