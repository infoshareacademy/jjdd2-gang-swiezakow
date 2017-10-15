package pl.infoshareacademy.webapp.searchCategoryCommandWeb;

import pl.infoshareacademy.AllegroCategory;

import java.util.List;

public class Card {

    private final List<AllegroCategory> allParents;
    private final String backgroundUrl;
    private final boolean isPromoted;

    public Card(List<AllegroCategory> allParents, String backgroundUrl, boolean isPromoted) {
        this.allParents = allParents;
        this.backgroundUrl = backgroundUrl;
        this.isPromoted = isPromoted;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public List<AllegroCategory> getAllParents() {
        return allParents;
    }

    public boolean isPromoted() {
        return isPromoted;
    }
}
