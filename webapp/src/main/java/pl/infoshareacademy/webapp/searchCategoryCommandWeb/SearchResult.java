package pl.infoshareacademy.webapp.searchCategoryCommandWeb;

import pl.infoshareacademy.AllegroCategory;

import java.util.List;

public class SearchResult {
    private AllegroCategory category;
    private List<ParentAllegroLink> parentLinks;
    private String backgroundImageUrl;
    private boolean isPromoted;
    private String categoryLink;

    public SearchResult(AllegroCategory category, List<ParentAllegroLink> parentLinks, String backgroundImageUrl, boolean isPromoted, String categoryLink) {
        this.category = category;
        this.parentLinks = parentLinks;
        this.backgroundImageUrl = backgroundImageUrl;
        this.isPromoted = isPromoted;
        this.categoryLink = categoryLink;
    }

    public AllegroCategory getCategory() {
        return category;
    }

    public List<ParentAllegroLink> getParentLinks() {
        return parentLinks;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public String getCategoryLink() {
        return categoryLink;
    }
}