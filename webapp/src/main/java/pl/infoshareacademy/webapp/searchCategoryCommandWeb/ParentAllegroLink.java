package pl.infoshareacademy.webapp.searchCategoryCommandWeb;

public class ParentAllegroLink {
    private String name;
    private String link;

    public ParentAllegroLink(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}