package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class SearchCategoryCommand {

    private static final String FILENAME = "Allegro_cathegories_2016-02-13.xml";
    private static final Logger logger = LogManager.getLogger(SearchCategoryCommand.class);
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
                logger.info("User has entered wrong number");
                System.out.println("Podano zły numer");
            } else if (result == Result.NO_RESULTS) {
                logger.info("User has entered wrong phrase");
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
        logger.info("User has entered " + line);

        AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();
        List<AllegroCategory> allCategories = allegroCategoryLoader.loadAllCategories(FILENAME);

        if (allCategories.isEmpty()) {
            logger.error("received empty list from AllegroCategoryLoader ");
            return Result.FATAL_ERROR;
        }

        AllegroCategorySearcher searcher = new AllegroCategorySearcher();
        List<AllegroCategory> matchingCategories = searcher.searchCategory(line, allCategories);

        if (matchingCategories.isEmpty()) {
            logger.warn("AllegroCategorySearcher returned empty list");
            return Result.NO_RESULTS;
        }

        AllegroCategory category = searcher.printCategoriesAndLetUserChoose(scanner, matchingCategories, allCategories);
        if (category == null) {
            logger.warn("AllegroCategory returned null results");
            return Result.BAD_NUMBER;
        }

        AllegroCategory parent = searcher.findById(allCategories, category.getParent());
        String link = generateLink(category, parent, line);
        System.out.println();
        System.out.println("W celu przejrzenia listy produktów skorzystaj z linka:");
        System.out.println(link);
        logger.debug("search has ended with success");
        return Result.SUCCESS;
    }

    private boolean readYesNoAnswer(Scanner scanner) {
        String line = scanner.nextLine();
        if ("tak".equals(line.toLowerCase())) {
            logger.info("User has entered 'yes'");
            return true;
        } else if ("nie".equals(line.toLowerCase())) {
            logger.info("User has entered 'no'");
            return false;
        } else {
            System.out.println("Niepoprawna odpowiedź. [Tak/Nie]");
            logger.warn("User has entered wrong answer");
            return readYesNoAnswer(scanner);
        }
    }

    private String generateLink(AllegroCategory category, AllegroCategory parent, String phrase){
        String phraseInLink = phrase.replace(" ", "-");
        logger.info("generating link for " + category + " and "+ parent + " and "+ phrase);
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
