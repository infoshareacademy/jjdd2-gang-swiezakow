package pl.infoshareacademy.webapp.allegro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllegroRestParent {
    private String id;

    public AllegroRestParent() {
    }

    public AllegroRestParent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
