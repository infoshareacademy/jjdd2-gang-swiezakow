package pl.infoshareacademy.webapp.categoryPickerCommandWeb;

public class PickerCommandCard {
    private boolean lastCategory;
    private int catId;
    private String categoryName;
    private String backgroundURL;
    private String allegroLink;
    private String childrenLink;
    private boolean isPromoted;

    public PickerCommandCard(boolean lastCategory, int catId,
                             String categoryName, String backgroundURL,
                             String allegroLink, String childrenLink,
                             boolean isPromoted) {
        this.lastCategory = lastCategory;
        this.catId = catId;
        this.categoryName = categoryName;
        this.backgroundURL = backgroundURL;
        this.allegroLink = allegroLink;
        this.childrenLink = childrenLink;
        this.isPromoted = isPromoted;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public String getChildrenLink() {
        return childrenLink;
    }

    public String getAllegroLink() {
        return allegroLink;
    }

    public String getBackgroundURL() {
        return backgroundURL;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCatId() {
        return catId;
    }

    public boolean isLastCategory() {
        return lastCategory;
    }
}
