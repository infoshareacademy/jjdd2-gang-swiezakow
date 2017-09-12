package pl.infoshareacademy;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static pl.infoshareacademy.AllegroLink.makeLink;

public class SearchByQuestionsCommand {

    public void run() {
        //Klucz | Wartosc
        //idKategorii  | List<AllegroCategory> podkategorie
        Map<Integer, List<AllegroCategory>> idToSubcategories;

        AllegroCategoryLoader loader = new AllegroCategoryLoader();
        try {
            idToSubcategories = loader.loadCategoryTree();
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
                    System.out.println("link i koniec " + makeLink(category.getName(), category.getCatID()));
                    break;
                } else {
                    //wymiana kategorii
                    categories = subcategories;
                    i = -1; // bo i++
                    size = subcategories.size();
                }
            }
        }
        System.out.println("Wracasz do głównego Menu.");
    }

    private void showQuestion(AllegroCategory category) {
        System.out.println("Czy interesuje Cię kategoria " + category.getName() + "?");
        System.out.print("[T/N] ");
    }

    private boolean readAnswer() {
        String answer;
        Scanner scanner = new Scanner(System.in);
        answer = scanner.nextLine();
        if (answer.toLowerCase().equals("tak") || answer.toLowerCase().equals("t")) {
            return true;
        } else if (answer.toLowerCase().equals("nie") || answer.toLowerCase().equals("n")) {
            System.out.println("Spróbuj ponownie");
            return false;
        } else {
            System.out.println("Nieprawidłowa odpowiedź");
            return readAnswer();
        }
    }
}
