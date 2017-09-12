package excercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static excercise.AllegroLink.makeLink;

public class SelByQ {

    public void run() {
        List<Category> c1subs = new ArrayList<>();
        Category c1s1 = new Category(123945, "telewizyjne", Collections.emptyList());
        Category c1s2 = new Category(110235, "sprzęt AGD", Collections.emptyList());
        c1subs.add(c1s1);
        c1subs.add(c1s2);
        Category c1 = new Category(67193, "elektronika", c1subs);
        Category c2 = new Category(3688, "książki", Collections.emptyList());
        Category c3 = new Category(76033, "odzież damska", Collections.emptyList());
        Category c4 = new Category(64478, "akcesoria biurowe", Collections.emptyList());

        List<Category> categories = new ArrayList<>();
        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
        categories.add(c4);


        System.out.println("Witaj!");

        for (int i = 0, size = categories.size(); i < size; i++) {

            Category ctgr = categories.get(i);
            askQ(ctgr);
            boolean isChosen = readAnswer();
            List<Category> sub = ctgr.getSubcategories();
            if (isChosen) {
                if (sub.isEmpty()) {
                    System.out.println("link i koniec " + makeLink(ctgr.getName(), ctgr.getId()));
                    break;
                } else {
                    System.out.println("obsl podkategorie");
                    categories = sub;
                    i = -1; // bo i++
                    size = sub.size();
                }
            }
        }
    }

    public void askQ(Category ctgr) {

        System.out.println("Czy interesuje Cię " + ctgr.getName() + "?");
        System.out.print("[T/N] ");
    }

    public boolean readAnswer() {
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

}
