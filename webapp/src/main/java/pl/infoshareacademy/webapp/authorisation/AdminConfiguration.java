package pl.infoshareacademy.webapp.authorisation;

import java.util.List;

public class AdminConfiguration {

    private List<String> admins;

    public AdminConfiguration() {
    }

    public AdminConfiguration(List<String> admins) {
        this.admins = admins;
    }

    public List<String> getAdmins() {
        return admins;
    }
}
