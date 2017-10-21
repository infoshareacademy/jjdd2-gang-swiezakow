package pl.infoshareacademy.webapp.searchQueryCommandWeb;

public class QueryCard {
    private final String backgroundUrl;
    private final String categoryName;
    private final boolean isPromoted;
    private final String phrase;

    public String getPhrase() {
        return phrase;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public QueryCard(String backgroundUrl, String categoryName, boolean isPromoted, String phrase) {
        this.backgroundUrl = backgroundUrl;
        this.categoryName = categoryName;
        this.isPromoted = isPromoted;
        this.phrase = phrase;
    }
}