package pl.infoshareacademy.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.Catalog;
import pl.infoshareacademy.SearchByQuestions;
import pl.infoshareacademy.SearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;



@WebServlet("SearchByQuestionsServlet")
public class SearchByQuestionsServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(SearchByQuestionsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print();

        //Zadaj pytanie dla glownej kategorii
        Catalog catalog = Catalog.catalogForFile(categoryFilePath);
        SearchByQuestions searchByQuestions = new SearchByQuestions(catalog);

        System.out.println("Witaj!");

        SearchResult result = searchByQuestions.chooseCategory(Catalog.ROOT_CATEGORY_ID);

        while (true) {
            if (result == null) {
                System.out.println("\nNiestety nie mamy kategorii która Cię interesuje.\n");
                LOGGER.debug("no suitable category");
                break;
            } else if (result.isLink()) {
                System.out.println("\nInteresujący Cię produkt możesz znaleźć korzystając z poniższego linka: \n\n " + makeLink(result.getCategoryName(), result.getCategoryId()));
                LOGGER.debug("link for category found");
                break;
            } else {
                showQuestion(result);
                int categoryId = result.getCategoryId();
                boolean isChosen = readAnswer();
                result = isChosen ? searchByQuestions.chooseCategory(categoryId) : searchByQuestions.omitCategory(categoryId);
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
