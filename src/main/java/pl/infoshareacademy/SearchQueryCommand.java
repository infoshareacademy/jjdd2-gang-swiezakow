package pl.infoshareacademy;

import java.util.List;
import java.util.Scanner;

public class SearchQueryCommand {

    private static String FILENAME = "Allegro_cathegories_2016-02-13.xml";

    public void queryCommand(Scanner scanner) {
        boolean result = true;
        while (result) {
           searchQuery(scanner);
           result = tryAgain(scanner);
        }
        System.out.println();
        System.out.println("Wracasz do głównego menu");
    }

    private boolean searchQuery(Scanner scanner){
        System.out.println("Czego szukasz?");
        String line = scanner.nextLine();

        AllegroCategoryLoader loader = new AllegroCategoryLoader();
        AllegroCategorySearcher searcher = new AllegroCategorySearcher();

        List<AllegroCategory> allCategories = loader.loadAllCategories(FILENAME);
        List<AllegroCategory> categories = searcher.searchCategory(line, allCategories);

        AllegroCategory category = searcher.printCategoriesAndLetUserChoose(scanner, categories, allCategories);

        if (category == null) {
            System.out.println("Nie znaleziono odpowiedniej kategorii");
            return true;
        } else {
            AllegroCategory parent = searcher.findById(allCategories, category.getParent());

            System.out.println("Aby znaleźć poszukiwany przedmiot użyj poniższego zapytania:");
            System.out.format("%s %s\n", parent.getName().toLowerCase(), category.getName().toLowerCase());
            return true;
        }
    }

    private boolean tryAgain(Scanner scanner){
        System.out.println("\nCzy chcesz spróbować ponownie?");
        String answer = scanner.nextLine();
        if(answer.toLowerCase().equals("tak")){
            return true;
        } else if(answer.toLowerCase().equals("nie")){
             return false;
        } else {
            System.out.println("Niepoprawna odpowiedź, spróbuj jeszcze raz");
            return tryAgain(scanner);
        }
    }
}
