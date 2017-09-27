package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class SearchCategoryCommand {

    private static final Logger logger = LogManager.getLogger(SearchCategoryCommand.class);

    enum Result {
        SUCCESS,
        NO_RESULTS,
        BAD_NUMBER,
        FATAL_ERROR
    }

    private final Configuration config = ConfigurationLoader.getConfiguration();
    private final String filename;
    private final AllegroCategoryLoader loader;
    private final AllegroCategorySearcher searcher;

    public SearchCategoryCommand(String filename, AllegroCategoryLoader allegroCategoryLoader, AllegroCategorySearcher allegroCategorySearcher) {
        this.filename = filename;
        this.loader = allegroCategoryLoader;
        this.searcher = allegroCategorySearcher;
    }

    public SearchCategoryCommand(String filename) {
        this.filename = filename;
        this.loader = new AllegroCategoryLoader();
        this.searcher = new AllegroCategorySearcher();
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
            run = readYesNoAnswer(new UserInput(scanner));
        }

        System.out.println();
        System.out.println("Wracasz do głównego Menu");
    }

    private Result search(Scanner scanner) {
        System.out.println("Czego szukasz?");
        String line = scanner.nextLine();
        logger.info("User has entered " + line);

        List<AllegroCategory> allCategories = loader.loadAllCategories(config.getFilePath());

        if (allCategories.isEmpty()) {
            logger.error("received empty list from AllegroCategoryLoader ");
            return Result.FATAL_ERROR;
        }

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

    boolean readYesNoAnswer(UserInput userInput) {
        String line = userInput.line();
        if ("tak".equals(line.toLowerCase())) {
            logger.info("User has entered 'yes'");
            return true;
        } else if ("nie".equals(line.toLowerCase())) {
            logger.info("User has entered 'no'");
            return false;
        } else {
            System.out.println("Niepoprawna odpowiedź. [Tak/Nie]");
            logger.warn("User has entered wrong answer");
            return readYesNoAnswer(userInput);
        }
    }

    String generateLink(AllegroCategory category, AllegroCategory parent, String phrase){
        String phraseInLink = phrase.replace(" ", "-");
        logger.info("generating link for " + category + " and "+ parent + " and "+ phrase);
        if(parent != null) {
            String parentInLink = parent.getName().replace(" ", "-");
            return String.format(config.getLinkForSCC1(), parentInLink, category.getParent(), phraseInLink);
        } else {
            String name = category.getName().toLowerCase().replace(" ", "-");
            return String.format(config.getLinkForSCC2(), name, phraseInLink);
        }
    }
}
