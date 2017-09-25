package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.Normalizer;

public class AllegroLink {

    private static final Logger logger = LogManager.getLogger(AllegroLink.class);

    public static String makeLink(String categoryName, int categoryId) {
        String normalizedCategoryName = categoryName.replace(' ', '-');
        String normalizedCategoryNameNoAccents = removeAccents(normalizedCategoryName);

        String link = String.format("https://allegro.pl/kategoria/%s-%d", normalizedCategoryNameNoAccents, categoryId);
        logger.info("returned link: " + link + " for category: " + categoryName + " id: " + categoryId);
        return link;
    }

    private static String removeAccents(String normalizedCategoryName) {
        return normalizedCategoryName == null ? null :
                Normalizer.normalize(normalizedCategoryName, Normalizer.Form.NFD)
                        .replaceAll("[^\\p{ASCII}]", "");
    }
}