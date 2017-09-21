package pl.infoshareacademy;

public class SearchResult {
    private boolean isLink;
    private String categoryName;
    private int categoryId;

    public SearchResult(String name, int catID, boolean isLink) {
        this.isLink = isLink;
        this.categoryName = name;
        this.categoryId = catID;
    }

    public boolean isLink() {
        return isLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
