package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllegroCategorySearcher {

    private static final Logger logger = LogManager.getLogger(AllegroCategorySearcher.class);

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

        logger.debug("Parent name = " + parentName);
        System.out.format("%d. %s -> %s\n", number, parentName, name);
    }

    public AllegroCategory findById(List<AllegroCategory> categories, int id) {
        for (AllegroCategory category : categories) {
            if (id == category.getCatID()){
                logger.debug("returned category: " + category + " with id " + category.getCatID());
                return category;
            }
        }
        return null;
    }

    private int readAnswer(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            logger.error("caught an exception during reading an answer");
            return -1;
        } finally {
            scanner.nextLine();
        }
    }

    public List<AllegroCategory> searchCategory(String line, List<AllegroCategory> allCategories) {

        List<AllegroCategory> matchingCategories = new ArrayList<>();
        String[] searchPhrases = line.split(" ");

        logger.debug("searching phrase has " + searchPhrases.length + " words");

        while (matchingCategories.isEmpty()) {
            for (String searchPhrase : searchPhrases) {
                if (searchPhrase.length() < 3) {
                    logger.debug("searching phrase length < 3");
                    continue;
                }
                for (AllegroCategory category : allCategories) {
                    if (category.getName().toLowerCase().contains(searchPhrase.toLowerCase())) {
                        matchingCategories.add(category);
                    }
                }
            }

            if (!matchingCategories.isEmpty()) {
                logger.debug("Found " + matchingCategories.size() + " results!");
                break;
            }

            searchPhrases = cutLastLetter(searchPhrases);
            if (searchPhrases.length == 0) {
                logger.warn("aborting search - too short word");
                break;
            }
            logger.debug("matching categories list was empty");
        }
        return matchingCategories;
    }

    private String[] cutLastLetter(String[] searchPhrases) {
        List<String> noweFrazy = new ArrayList<>();
        for (int i = 0; i < searchPhrases.length; i++) {
            if (searchPhrases[i].length() >= 4) {
                noweFrazy.add(searchPhrases[i].substring(0, searchPhrases[i].length() - 1));
            }
        }

        logger.debug("result of cutting last letter: " + String.join(", ", noweFrazy));
        return noweFrazy.toArray(new String[noweFrazy.size()]);
    }
}
