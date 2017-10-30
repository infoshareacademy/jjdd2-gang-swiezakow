package pl.infoshareacademy.webapp.authorisation;

import java.util.List;

public class AdminConfiguration {

    private List<String> adminsList;

    public AdminConfiguration() {
    }

    public AdminConfiguration(List<String> adminsList) {
        this.adminsList = adminsList;
    }

    public List<String> getAdminList() {
        return adminsList;
    }
}
