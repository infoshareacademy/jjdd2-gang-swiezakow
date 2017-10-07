package pl.infoshareacademy.webapp.SearchQueryCommandWeb;

public class QueryCard {
    private final String backgroundUrl;
    private final String categoryName;

    public String getPhrase() {
        return phrase;
    }

    private final String phrase;

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public QueryCard(String backgroundUrl, String categoryName, String phrase) {
        this.backgroundUrl = backgroundUrl;
        this.categoryName = categoryName;
        this.phrase = phrase;
    }
}
