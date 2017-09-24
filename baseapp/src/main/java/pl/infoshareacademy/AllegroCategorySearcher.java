package pl.infoshareacademy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AllegroCategorySearcher {

    public AllegroCategory printCategoriesAndLetUserChoose(Scanner scanner, List<AllegroCategory> matchingCategories, List<AllegroCategory> allCategories) {
        for (int i = 0; i < matchingCategories.size(); i++){
            printCategory(i + 1, matchingCategories.get(i), allCategories);
        }

        if (!matchingCategories.isEmpty()) {
            System.out.println("Podaj numer kategorii: ");
            int number = readAnswer(scanner);
            if (number > 0 && number <= matchingCategories.size()) {
                return matchingCategories.get(number - 1);
            }
        }
        return null;
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

    public AllegroCategory findById(List<AllegroCategory> categories, int id) {
        for (AllegroCategory category : categories) {
            if (id == category.getCatID()){
                return category;
            }
        }
        return null;
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

    String[] cutLastLetter(String[] searchPhrases) {
        List<String> noweFrazy = Arrays.stream(searchPhrases)
                .filter(s -> s.length() >= 4)
                .map(s -> s.substring(0, s.length() - 1))
                .collect(Collectors.toList());

        return noweFrazy.toArray(new String[noweFrazy.size()]);
    }
}
