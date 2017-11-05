package pl.infoshareacademy.webapp.statistics;

public enum StatisticEvents {
    CATEGORY1_CHOICE,
    CATEGORY2_CHOICE,
    CATEGORY3_SEARCH,
    CATEGORY4_SEARCH,

    MENU_ENTRY,
    CATEGORY1_ENTRY("Seria pytań"),
    CATEGORY2_ENTRY("Wybierz produkt"),
    CATEGORY3_ENTRY("Katalog Allegro"),
    CATEGORY4_ENTRY("Asystent Allegro");

    private String name;

    StatisticEvents() {
        this.name = null;
    }

    StatisticEvents(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
