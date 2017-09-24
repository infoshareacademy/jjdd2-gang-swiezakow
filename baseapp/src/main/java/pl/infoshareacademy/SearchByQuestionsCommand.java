package pl.infoshareacademy;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static pl.infoshareacademy.AllegroLink.makeLink;

public class SearchByQuestionsCommand {
    private String categoryFilePath;

    public SearchByQuestionsCommand(String categoryFilePath) {
        this.categoryFilePath = categoryFilePath;
    }

    public void run() {
        //Zadaj pytanie dla glownej kategorii
        Catalog catalog = Catalog.catalogForFile(categoryFilePath);
        SearchByQuestions searchByQuestions = new SearchByQuestions(catalog);

        System.out.println("Witaj!");

        SearchResult result = searchByQuestions.chooseCategory(Catalog.ROOT_CATEGORY_ID);

        while (true) {
            if (result == null) {
                System.out.println("\nNiestety nie mamy kategorii ktora pasuje.\n");
                break;
            } else if (result.isLink()) {
                System.out.println("\nInteresujący Cię produkt możesz znaleźć korzystając z poniższego linka: \n\n " + makeLink(result.getCategoryName(), result.getCategoryId()));
                break;
            } else {
                showQuestion(result);
                int categoryId = result.getCategoryId();
                boolean isChosen = readAnswer();
                result = isChosen ? searchByQuestions.chooseCategory(categoryId) : searchByQuestions.omitCategory(categoryId);
            }
        }

        System.out.println("\nWracasz do głównego Menu.\n");
    }

    private void showQuestion(SearchResult searchResult) {
        System.out.println("\nCzy interesuje Cię kategoria " + searchResult.getCategoryName() + "?\n");
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