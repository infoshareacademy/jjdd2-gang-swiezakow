package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Scanner;

import static pl.infoshareacademy.AllegroLink.makeLink;

public class SearchByQuestionsCommand {
    private static final Logger LOGGER = LogManager.getLogger(SearchByQuestionsCommand.class);

    private String categoryFilePath;

    public SearchByQuestionsCommand(String categoryFilePath) {
        this.categoryFilePath = categoryFilePath;
    }

    public void run() {
        //Zadaj pytanie dla glownej kategorii
        Catalog catalog = Catalog.catalogForFile(categoryFilePath);
        SearchByQuestions searchByQuestions = new SearchByQuestions(catalog);

        System.out.println("Witaj!");

        Optional<SearchResult> result = Optional.of(searchByQuestions.chooseCategory(Catalog.ROOT_CATEGORY_ID));

        while (true) {
            if (!result.isPresent()) {
                System.out.println("\nNiestety nie mamy kategorii która Cię interesuje.\n");
                LOGGER.debug("no suitable category");
                break;
            } else if (result.get().isLink()) {
                System.out.println("\nInteresujący Cię produkt możesz znaleźć korzystając z poniższego linka: \n\n " + makeLink(result.get().getCategoryName(), result.get().getCategoryId()));
                LOGGER.debug("link for category found");
                break;
            } else {
                showQuestion(result.get());
                int categoryId = result.get().getCategoryId();
                boolean isChosen = readAnswer();
                result = isChosen ? Optional.of(searchByQuestions.chooseCategory(categoryId)) : searchByQuestions.omitCategory(categoryId);
                LOGGER.debug(isChosen ? "user selected yes" : "user selected no");

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