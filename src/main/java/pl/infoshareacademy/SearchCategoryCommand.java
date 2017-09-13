package pl.infoshareacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchCategoryCommand {

    private static final String PATH_TO_XML = "Allegro_cathegories_2016-02-13.xml";

    enum Result {
        SUCCESS,
        NO_RESULTS,
        BAD_NUMBER,
        FATAL_ERROR
    }

    public void handleCommand(Scanner scanner) {
        boolean run = true;

        while(run) {
            Result result = search(scanner);

            if (result == Result.BAD_NUMBER) {
                System.out.println("Podano zły numer");
            } else if (result == Result.NO_RESULTS) {
                System.out.println("Przykro mi, nie znaleziono odpowiedniej kategorii.");
            } else if (result == Result.FATAL_ERROR) {
                System.out.println("Nie można wczytać kategorii. Wracasz do menu");
                return;
            }

            System.out.println("Czy chcesz spróbować ponownie? [Tak/Nie]");
            run = readYesNoAnswer(scanner);
        }

        System.out.println();
        System.out.println("Wracasz do głównego Menu");
    }

    private Result search(Scanner scanner) {
        System.out.println("Czego szukasz?");
        String line = scanner.nextLine();

        AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();
        List<AllegroCategory> allCategories = allegroCategoryLoader.loadAllCategories(PATH_TO_XML);

        if (allCategories.isEmpty()) {
            return Result.FATAL_ERROR;
        }

        List<AllegroCategory> matchingCategories = searchCategory(line, allCategories);

        for (int i = 0; i < matchingCategories.size(); i++){
            printCategory(i + 1, matchingCategories.get(i), allCategories);
        }

        if (!matchingCategories.isEmpty()) {
            System.out.println("Podaj numer kategorii: ");
            int number = readAnswer(scanner);
            if (number > 0 && number <= matchingCategories.size()) {
                String link = generateLink(matchingCategories.get(number - 1), allCategories, line);
                System.out.println();
                System.out.println("W celu przejrzenia listy produktów skorzystaj z linka:");
                System.out.println(link);
                return Result.SUCCESS;
            } else {
                return Result.BAD_NUMBER;
            }
        }
        return Result.NO_RESULTS;
    }

    private boolean readYesNoAnswer(Scanner scanner) {
        String line = scanner.nextLine();
        if ("tak".equals(line.toLowerCase())) {
            return true;
        } else if ("nie".equals(line.toLowerCase())) {
            return false;
        } else {
            System.out.println("Niepoprawna odpowiedź. [Tak/Nie]");
            return readYesNoAnswer(scanner);
        }
    }

    private int readAnswer(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            return -1;
        } finally {
            scanner.nextLine();
        }
    }

    private void printCategory(int number, AllegroCategory category, List<AllegroCategory> allCategories) {
        int parentId = category.getParent();
        String name = category.getName();

        String parentName;
        if (parentId != 0) {
            AllegroCategory parent = findById(allCategories, parentId);
            parentName = parent.getName();
        } else {
            parentName = "Główna kategoria";
        }

        System.out.format("%d. %s -> %s\n", number, parentName, name);
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

    public List<AllegroCategory> searchCategory(String line, List<AllegroCategory> allCategories) {

        List<AllegroCategory> matchingCategories = new ArrayList<>();
        String[] searchPhrases = line.split(" ");
        while (matchingCategories.isEmpty()) {
            for (String searchPhrase : searchPhrases) {
                if (searchPhrase.length() < 3) {
                    continue;
                }
                for (AllegroCategory category : allCategories) {
                    if (category.getName().toLowerCase().contains(searchPhrase.toLowerCase())) {
                        matchingCategories.add(category);
                    }
                }
            }
            searchPhrases = cutLastLetter(searchPhrases);
            if (searchPhrases.length == 0) {
                break;
            }
        }
        return matchingCategories;
    }
}
