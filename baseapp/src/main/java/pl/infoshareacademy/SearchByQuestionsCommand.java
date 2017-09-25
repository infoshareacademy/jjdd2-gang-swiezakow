package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static pl.infoshareacademy.AllegroLink.makeLink;

public class SearchByQuestionsCommand {

    private static final Logger logger = LogManager.getLogger(SearchByQuestionsCommand.class);

    private String categoryFilePath;

    public SearchByQuestionsCommand(String categoryFilePath) {
        this.categoryFilePath = categoryFilePath;
    }

    public void run() {
        AllegroCategoryLoader loader = new AllegroCategoryLoader();
        Map<Integer, List<AllegroCategory>> idToSubcategories = loader.loadCategoryTree(categoryFilePath);
        int rootParent = 0;
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
                    logger.debug("Searching has ended");
                    break;
                } else {
                    categories = subcategories;
                    i = -1;
                    size = subcategories.size();
                }
            }
        }
        System.out.println("\nWracasz do głównego Menu.\n");
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
            logger.info("User has entered 'yes'");
            return true;
        } else if (answer.toLowerCase().equals("nie") || answer.toLowerCase().equals("n")) {
            logger.info("User has entered 'no'");
            return false;
        } else {
            System.out.println("Nieprawidłowa odpowiedź");
            logger.info("User has entered: " + answer);
            logger.warn("User has entered wrong answer");
            return readAnswer();
        }
    }
}