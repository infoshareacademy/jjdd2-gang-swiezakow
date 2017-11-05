package pl.infoshareacademy.webapp.allegro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllegroRestCategories {
    private List<AllegroRestCategory> categories;

    public AllegroRestCategories() {
    }

    public AllegroRestCategories(List<AllegroRestCategory> categories) {
        this.categories = categories;
    }

    public List<AllegroRestCategory> getCategories() {
        return categories;
    }
}
