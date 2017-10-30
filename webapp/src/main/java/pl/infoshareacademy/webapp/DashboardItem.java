package pl.infoshareacademy.webapp;

public class DashboardItem {
    private String name;
    private String url;
    private String allegroLink;

    public DashboardItem(String name, String url, String allegroLink) {
        this.name = name;
        this.url = url;
        this.allegroLink = allegroLink;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getAllegroLink() {
        return allegroLink;
    }
}
