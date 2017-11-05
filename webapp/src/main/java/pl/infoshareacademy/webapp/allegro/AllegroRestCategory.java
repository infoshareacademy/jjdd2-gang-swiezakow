package pl.infoshareacademy.webapp.allegro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllegroRestCategory {
    private String id;
    private String name;
    private AllegroRestParent parent;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AllegroRestParent getParent() {
        return parent;
    }
}
