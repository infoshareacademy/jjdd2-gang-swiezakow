package pl.infoshareacademy;

import java.text.Normalizer;

public class AllegroLink {
    public static String makeLink(String categoryName, int categoryId) {
        String normalizedCategoryName = categoryName.replace(' ', '-');
        String normalizedCategoryNameNoAccents = removeAccents(normalizedCategoryName);
        return String.format("https://allegro.pl/kategoria/%s-%d", normalizedCategoryNameNoAccents, categoryId);
    }

    private static String removeAccents(String normalizedCategoryName) {
        return normalizedCategoryName == null ? null :
                Normalizer.normalize(normalizedCategoryName, Normalizer.Form.NFD)
                        .replaceAll("[^\\p{ASCII}]", "");
    }

}



