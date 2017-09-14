package pl.infoshareacademy;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static pl.infoshareacademy.AllegroLink.makeLink;

public class SearchByQuestionsCommand {

    private static String FILENAME = "Allegro_cathegories_2016-02-13.xml";


    public void run() {
        //Klucz | Wartosc
        //idKategorii  | List<AllegroCategory> podkategorie
        Map<Integer, List<AllegroCategory>> idToSubcategories;

        AllegroCategoryLoader loader = new AllegroCategoryLoader();
        try {
            idToSubcategories = loader.loadCategoryTree(FILENAME);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        int rootParent = 0;
        //Zaincjalizuj glownymi kategoriami
        List<AllegroCategory> categories = idToSubcategories.get(rootParent);

        System.out.println("Witaj!");

        for (int i = 0, size = categories.size(); i < size; i++) {
            AllegroCategory category = categories.get(i);
            showQuestion(category);
            boolean isChosen = readAnswer();
            List<AllegroCategory> subcategories = idToSubcategories.get(category.getCatID());
            if (isChosen) {
                if (null == subcategories || subcategories.isEmpty()) {
                    System.out.println("\nInteresujący Cię produkt możesz znaleźć korzystając z poniższego linka: \n\n " + makeLink(category.getName(), category.getCatID()));
                    break;
                } else {
                    //wymiana kategorii
                    categories = subcategories;
                    i = -1; // bo i++
                    size = subcategories.size(); // reset rozmiaru
                }
            }
        }
        System.out.println("\nWracasz do głównego Menu.");
    }

    private void showQuestion(AllegroCategory category) {
        System.out.println("\nCzy interesuje Cię kategoria " + category.getName() + "?\n");
        System.out.print("[T/N] ");
    }

    private boolean readAnswer() {
        String answer;
        Scanner scanner = new Scanner(System.in);
        answer = scanner.nextLine();
        if (answer.toLowerCase().equals("tak") || answer.toLowerCase().equals("t")) {
            return true;
        } else if (answer.toLowerCase().equals("nie") || answer.toLowerCase().equals("n")) {
            return false;
        } else {
            System.out.println("Nieprawidłowa odpowiedź");
            return readAnswer();
        }
    }
}
