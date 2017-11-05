package pl.infoshareacademy.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroLink;
import pl.infoshareacademy.Configuration;
import pl.infoshareacademy.ConfigurationLoader;
import pl.infoshareacademy.webapp.allegro.AllegroClient;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Stateless
public class LinkService {

    private static final Logger logger = LogManager.getLogger(LinkService.class);

    @Inject
    private AllegroClient allegroClient;

    private final Configuration config = ConfigurationLoader.getConfiguration();

    public String makeLink(String name, Integer catId) {
        String rewrittenId = allegroClient.getRewrittenId(catId);
        return AllegroLink.makeLink(name, rewrittenId);
    }

    public String makeLink(AllegroCategory category, String phrase) {
        String phraseInLink = phrase.replace(" ", "-");
        String name = category.getName().toLowerCase().replace(" ", "-");
        String rewrittenId = allegroClient.getRewrittenId(category.getCatID());

        try {
            return String.format(config.getLinkForSCC(),
                    URLEncoder.encode(name, StandardCharsets.UTF_8.name()),
                    rewrittenId,
                    URLEncoder.encode(phraseInLink, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            logger.error("Cannot encode url", e);
            throw new RuntimeException(e);
        }
    }
}