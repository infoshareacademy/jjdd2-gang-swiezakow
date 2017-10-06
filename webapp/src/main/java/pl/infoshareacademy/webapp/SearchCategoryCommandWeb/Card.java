package pl.infoshareacademy.webapp.SearchCategoryCommandWeb;

import pl.infoshareacademy.AllegroCategory;

import java.util.List;

public class Card {

    private final List<AllegroCategory> allParents;
    private final String backgroundUrl;

    public Card(List<AllegroCategory> allParents, String backgroundUrl) {
        this.allParents = allParents;
        this.backgroundUrl = backgroundUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public List<AllegroCategory> getAllParents() {
        return allParents;
    }
}
