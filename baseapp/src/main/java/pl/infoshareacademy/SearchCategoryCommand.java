package pl.infoshareacademy;

import java.util.List;
import java.util.Scanner;

public class SearchCategoryCommand {

    private static final String FILENAME = "Allegro_cathegories_2016-02-13.xml";

    enum Result {
        SUCCESS,
        NO_RESULTS,
        BAD_NUMBER,
        FATAL_ERROR
    }

    private AllegroCategoryLoader loader;
    private AllegroCategorySearcher searcher;

    public SearchCategoryCommand(AllegroCategoryLoader allegroCategoryLoader, AllegroCategorySearcher allegroCategorySearcher) {
        this.loader = allegroCategoryLoader;
        this.searcher = allegroCategorySearcher;
    }

    public SearchCategoryCommand() {
        this.loader = new AllegroCategoryLoader();
        this.searcher = new AllegroCategorySearcher();
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
            run = readYesNoAnswer(new UserInput(scanner));
        }

        System.out.println();
        System.out.println("Wracasz do głównego Menu");
    }

    private Result search(Scanner scanner) {
        System.out.println("Czego szukasz?");
        String line = scanner.nextLine();

        List<AllegroCategory> allCategories = loader.loadAllCategories(FILENAME);

        if (allCategories.isEmpty()) {
            return Result.FATAL_ERROR;
        }

        List<AllegroCategory> matchingCategories = searcher.searchCategory(line, allCategories);

        if (matchingCategories.isEmpty()) {
            return Result.NO_RESULTS;
        }

        AllegroCategory category = searcher.printCategoriesAndLetUserChoose(scanner, matchingCategories, allCategories);
        if (category == null) {
            return Result.BAD_NUMBER;
        }

        AllegroCategory parent = searcher.findById(allCategories, category.getParent());
        String link = generateLink(category, parent, line);
        System.out.println();
        System.out.println("W celu przejrzenia listy produktów skorzystaj z linka:");
        System.out.println(link);
        return Result.SUCCESS;
    }

    boolean readYesNoAnswer(UserInput userInput) {
        String line = userInput.line();
        if ("tak".equals(line.toLowerCase())) {
            return true;
        } else if ("nie".equals(line.toLowerCase())) {
            return false;
        } else {
            System.out.println("Niepoprawna odpowiedź. [Tak/Nie]");
            return readYesNoAnswer(userInput);
        }
    }

    String generateLink(AllegroCategory category, AllegroCategory parent, String phrase){
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
