package pl.infoshareacademy.webapp.SearchCategoryCommandWeb;

public class CategoryPicture {
    private int id;
    private String url;

    private CategoryPicture () {}

    public CategoryPicture(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
