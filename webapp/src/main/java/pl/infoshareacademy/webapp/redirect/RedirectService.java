package pl.infoshareacademy.webapp.redirect;

import javax.ejb.Stateless;

@Stateless
public class RedirectService {
    private final String url = "/webapp/redirect?";

    public String getSecretUrl(Integer catId, Integer menuId, String phrase) {
        return String.format("%sid=%d&phrase=%s&menuId=%d", url, catId, phrase, menuId);
    }
}
