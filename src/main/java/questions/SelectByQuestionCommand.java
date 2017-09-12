package questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static questions.AllegroLink.makeLink;

public class SelectByQuestionCommand {

    public void run() {
        List<Category> categories = getTestCategories();

        System.out.println("Witaj!");

        for (int i = 0, size = categories.size(); i < size; i++) {
            Category category = categories.get(i);
            showQuestion(category);
            boolean isChosen = readAnswer();
            List<Category> subcategories = category.getSubcategories();
            if (isChosen) {
                if (subcategories.isEmpty()) {
                    System.out.println("link i koniec " + makeLink(category.getName(), category.getId()));
                    break;
                } else {
                    //wymiana kategorii
                    categories = subcategories;
                    i = -1; // bo i++
                    size = subcategories.size();
                }
            }
        }
    }


    private void showQuestion(Category ctgr) {
        System.out.println(ctgr.getQuestion());
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
            return  false;
        } else {
            System.out.println("Nieprawidłowa odpowiedź");
             return readAnswer();
        }
    }

    private List<Category> getTestCategories() {
        List<Category> c1subs = new ArrayList<>();
        Category c1s1 = new Category(123945, "telewizyjne", Collections.emptyList());
        Category c1s2 = new Category(110235, "sprzęt AGD", Collections.emptyList());
        c1subs.add(c1s1);
        c1subs.add(c1s2);
        Category c1 = new Category(67193, "Elektronika", c1subs);
        Category c2 = new Category(3688, "Książki", Collections.emptyList());
        Category c3 = new Category(76033, "Odzież damska", Collections.emptyList());
        Category c4 = new Category(64478, "Akcesoria biurowe", Collections.emptyList());

        List<Category> categories = new ArrayList<>();
        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
        categories.add(c4);
        return categories;
    }
}
