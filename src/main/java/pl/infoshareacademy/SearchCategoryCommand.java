package pl.infoshareacademy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class SearchCategoryCommand {

    public void handleCommand(Scanner scanner) throws ParserConfigurationException, SAXException, IOException {
        System.out.println("Czego szukasz?");
        String line = scanner.nextLine();

        AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();
        List<AllegroCategory> list = allegroCategoryLoader.loadAllCategories();

        String[] searchPhrases = line.split(" ");
        Integer matchedCategories = 0;
        Map<Integer, AllegroCategory> mapa = new HashMap<>();
        while (matchedCategories == 0) {
            for (String s : searchPhrases) {
                if(s.length() < 3) {
                    continue;
                }
                for (AllegroCategory category : list) {
                    if (category.getName().toLowerCase().contains(s.toLowerCase())) {
                        matchedCategories++;
                        mapa.put(matchedCategories, category);
                        AllegroCategory parent = findById(list, category.getParent());
                        if (parent == null) {
                            System.out.println(matchedCategories + ". Główna kategoria -> " + category.getName());
                        } else {
                            System.out.println( matchedCategories + "." + parent.getName() + "->" + category.getName());
                        }
                    }
                }
            }
            searchPhrases = cutLastLetter(searchPhrases);
            if (searchPhrases.length == 0) {
                break;
            }
        }

        if (matchedCategories == 0) {
            System.out.println("Przykro nam, nie znaleźliśmy odpowiedniej kategorii. Spróbuj jeszcze raz.");
        } else {
            try {
                System.out.println("Podaj numer kategorii");
                final int i = scanner.nextInt();
                scanner.nextLine();
                String link = generateLink(mapa.get(i), list, line);
                System.out.println();
                System.out.println("W celu przejrzenia listy produktów skorzystaj z linka:");
                System.out.println(link);

            } catch (Exception e) {
                System.out.println("Błędny numer kategorii, spróbuj jeszcze raz");
            }
        }

        System.out.println();
        System.out.println("Wracasz do głównego Menu");
    }

    private String[] cutLastLetter(String[] searchPhrases) {
        List<String> noweFrazy = new ArrayList<>();
        for (int i = 0; i < searchPhrases.length; i++) {
            if (searchPhrases[i].length() >= 4) {
                noweFrazy.add(searchPhrases[i].substring(0, searchPhrases[i].length() - 1));
            }
        }
        return noweFrazy.toArray(new String[noweFrazy.size()]);
    }

    private AllegroCategory findById(List<AllegroCategory> categories, int id) {
        for (AllegroCategory category : categories) {
            if(id == category.getCatID()){
                return category;
            }
        }
        return null;
    }
    private String generateLink(AllegroCategory category, List<AllegroCategory> list, String phrase){
        AllegroCategory parent = findById(list, category.getParent());
        String phraseInLink = phrase.replace(" ", "-");
        if(parent != null) {
            String parentInLink = parent.getName().replace(" ", "-");
            return "https://allegro.pl/kategoria/" + parentInLink
                    + "-" + category.getParent() + "?string=" + phraseInLink;
        } else {
            String name = category.getName().toLowerCase().replace(" ", "-");
            return "https://allegro.pl/kategoria/" + name
                    + "?string=" + phraseInLink;
        }
    }
}
