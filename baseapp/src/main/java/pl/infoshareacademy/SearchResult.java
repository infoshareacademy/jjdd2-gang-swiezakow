package pl.infoshareacademy;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return isLink == that.isLink &&
                categoryId == that.categoryId &&
                Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLink, categoryName, categoryId);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "isLink=" + isLink +
                ", categoryName='" + categoryName + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
