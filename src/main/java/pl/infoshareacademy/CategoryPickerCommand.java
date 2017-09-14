package pl.infoshareacademy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CategoryPickerCommand {
    AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();

    private HashMap<Integer, List<AllegroCategory>> allegroCategoryTree = (HashMap<Integer, List<AllegroCategory>>) allegroCategoryLoader.loadCategoryTree();
    private ArrayList<Integer> choosenCategoryHistory = new ArrayList<>();
    private Scanner odczyt = new Scanner(System.in);


    private Integer choosenCategory = 0;
    private Integer helper = 0;


    public CategoryPickerCommand() throws ParserConfigurationException, SAXException, IOException {
    }

    //display the list of category, started by general
    public void showChildrenCategory(){
        try {

            System.out.println("Przeglądaj kategorie w celu wygenerowania linku do szukanego przedmiotu:");
            System.out.println("Możesz wygenerowować link dla danej kategorii wpisując: "
                    + (char)27 +"[30;44mgenerate" + (char)27 + "[0m "
                    + (char)27 + "[30;44mnr_kategorii" + (char)27 +"[0m ");
            System.out.println("Przejść do konkretniejszych kategorii:                  "
                    + (char)27 +"[30;44menter" + (char)27 + "[0m "
                    + (char)27 + "[30;44mnr_kategorii" + (char)27 +"[0m ");
            System.out.println("Cofnąć się do poprzedniej kategorii:                    "
                    + (char)27 +"[30;44mback" + (char)27 +"[0m ");
            System.out.println();


            for (int i = 0; i < allegroCategoryTree.get(helper).size(); i++) {
                if (choosenCategory == (allegroCategoryTree.get(helper).get(i).getCatPosition() + 1)) {
                    helper = allegroCategoryTree.get(helper).get(i).getCatID();
                    choosenCategoryHistory.add(helper);
                }

            }

            for (int i = 0; i < allegroCategoryTree.get(helper).size(); i++) {
                System.out.println((allegroCategoryTree.get(helper).get(i).getCatPosition() + 1) + ". " + allegroCategoryTree.get(helper).get(i).toString());
            }
            System.out.println("Co chcesz zrobic?");

            this.whatDoYouWnatToDo();
        } catch (java.lang.NullPointerException e) {
            System.out.println((char)27 +"[31mBrak podkategorii, możesz wygenerować link lub się cofnąć" + (char)27 +"[0m ");
            choosenCategory = 0;
            helper = this.choosenCategoryHistory.get(this.choosenCategoryHistory.size() - 2);
            this.choosenCategoryHistory.remove(this.choosenCategoryHistory.size()-1);
            this.showChildrenCategory();
        }

    }

    //method which read input from user, show next category or generate link for choosen category or back to previous category
    private void whatDoYouWnatToDo(){
        System.out.println(choosenCategoryHistory);
        String what = odczyt.nextLine();
        String[] userChoose;
        userChoose = what.split(" ");

        if (userChoose[0].equals(CategoryCommands.ENTER.getCommands())){
            if (Integer.parseInt(userChoose[1]) > 0 && Integer.parseInt(userChoose[1]) < this.allegroCategoryTree.get(helper).size()) {
                choosenCategory = Integer.parseInt(userChoose[1]);
                this.showChildrenCategory();
            } else if (this.allegroCategoryTree.get(helper).size() == 0) {
                System.out.println("Koniec dalszych kategorii");
                helper = this.choosenCategoryHistory.get(this.choosenCategoryHistory.size() - 1);
                this.choosenCategoryHistory.remove(this.choosenCategoryHistory.size() - 1);
                this.showChildrenCategory();
            } else {
                System.out.println("Brak istniejącej kategorii do której chcesz wejść");
                System.out.println("Spróbuj ponownie \n \n");
                this.showChildrenCategory();
            }

        }
        try {
            if (userChoose[0].equals(CategoryCommands.GENERATE.getCommands())){
                
                System.out.println("Wygenerowany link dla: " + userChoose[1] + ": " + this.allegroCategoryTree.get(helper).get(Integer.parseInt(userChoose[1])-1).getName());
                //generuj link
            }
        } catch (java.lang.IndexOutOfBoundsException e){
            System.out.println("Błędny numer kategorii, spróbuj ponownie \n \n \n");
            this.showChildrenCategory();
        }

        if (userChoose[0].equals(CategoryCommands.BACK.getCommands())){
            if (choosenCategoryHistory.size()==1){
                helper = 0;
                this.choosenCategory = 0;
                this.showChildrenCategory();
            } else if (choosenCategoryHistory.isEmpty()) {
                System.out.println("Przechodzisz do głównego menu");
            }
            else {
                this.helper = choosenCategoryHistory.get(choosenCategoryHistory.size() - 1);
                choosenCategoryHistory.remove(choosenCategoryHistory.size() - 1);
                this.choosenCategory = 0;
                this.showChildrenCategory();
            }
        }

        if (!(userChoose[0].equals(CategoryCommands.BACK.getCommands()) || userChoose[0].equals(CategoryCommands.ENTER.getCommands()) ||
                userChoose[0].equals(CategoryCommands.GENERATE.getCommands()))) {
            System.out.println("Wprowadź poprawną komendę \n \n \n ");
            this.showChildrenCategory();
        }
    }


}



enum CategoryCommands {
    ENTER("enter"), GENERATE("generate"), BACK("back");

    private final String commands;

    CategoryCommands(String commands){
        this.commands = commands;
    }

    public String getCommands() {
        return commands;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
