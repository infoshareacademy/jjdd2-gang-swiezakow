package pl.infoshareacademy.webapp.categoryPickerCommandWeb;

public class PickerCommandCard {
    private boolean lastCategory;
    private int catId;
    private String categoryName;
    private String backgroundURL;
    private String allegroLink;

    public PickerCommandCard(boolean lastCategory, int catId, String categoryName, String backgroundURL, String allegroLink) {
        this.lastCategory = lastCategory;
        this.catId = catId;
        this.categoryName = categoryName;
        this.backgroundURL = backgroundURL;
        this.allegroLink = allegroLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getBackgroundURL() {
        return backgroundURL;
    }

    public String getAllegroLink() {
        return allegroLink;
    }

    public int getCatId() {
        return catId;
    }

    public boolean isLastCategory() {
        return lastCategory;
    }

}
