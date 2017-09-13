package pl.infoshareacademy;

import java.util.List;
import java.util.Scanner;

public class SearchQueryCommand {

    private static String FILENAME = "Allegro_cathegories_2016-02-13.xml";

    public void queryCommand(Scanner scanner){
        System.out.println("Czego szukasz?");
        String line = scanner.nextLine();

        AllegroCategoryLoader loader = new AllegroCategoryLoader();
        AllegroCategorySearcher searcher = new AllegroCategorySearcher();

        List<AllegroCategory> allCategories = loader.loadAllCategories(FILENAME);
        List<AllegroCategory> categories = searcher.searchCategory(line, allCategories);

        AllegroCategory category = searcher.printCategoriesAndLetUserChoose(scanner, categories, allCategories);
        AllegroCategory parent = searcher.findById(allCategories, category.getParent());

        System.out.println("Aby znaleźć poszukiwany przedmiot użyj poniższego zapytania:");
        System.out.format("%s %s\n", parent.getName(), category.getName());
        System.out.println();
        System.out.println("Wracasz do głównego menu");
    }
}
