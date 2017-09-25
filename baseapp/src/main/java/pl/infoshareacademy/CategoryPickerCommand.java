package pl.infoshareacademy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CategoryPickerCommand {
    AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();
    static final String FILENAME = "Allegro_cathegories_2016-02-13.xml";
    HashMap<Integer, List<AllegroCategory>> allegroCategoryTree = (HashMap<Integer, List<AllegroCategory>>) allegroCategoryLoader.loadCategoryTree(FILENAME);
    ArrayList<Integer> choosenCategoryHistory = new ArrayList<>();
    Scanner inputReader = new Scanner(System.in);


    Integer choosenCategory = 0;
    Integer helper = 0;

    public CategoryPickerCommand() throws ParserConfigurationException, SAXException, IOException {
    }

    //display the list of category, started by general
    public void showChildrenCategory(){
        try {

            this.showAllOptions();


            for (int i = 0; i < allegroCategoryTree.get(helper).size(); i++) {
                if (choosenCategory== (allegroCategoryTree.get(helper).get(i).getCatPosition() + 1)) {
                    helper = allegroCategoryTree.get(helper).get(i).getCatID();
                    choosenCategoryHistory.add(helper);
                }
            }

            for (int i = 0; i < allegroCategoryTree.get(helper).size(); i++) {
                System.out.println((allegroCategoryTree.get(helper).get(i).getCatPosition() + 1) + ". " + allegroCategoryTree.get(helper).get(i).toString());
            }

            System.out.println("Co chcesz zrobic?");

            this.veryficationUserInput();

        } catch (java.lang.NullPointerException e) {
            System.out.println((char)27 +"[31mBrak podkategorii, możesz wygenerować link lub się cofnąć" + (char)27 +"[0m ");
            choosenCategory = 0;
            helper = this.choosenCategoryHistory.get(this.choosenCategoryHistory.size() - 2);
            this.choosenCategoryHistory.remove(this.choosenCategoryHistory.size()-1);
            this.showChildrenCategory();
        }

    }

    //method which read input from user, show next category or generate link for choosen category or back to previous category
    private void veryficationUserInput(){
        System.out.println(this.choosenCategoryHistory);
        String inputHelper = inputReader.nextLine();
        String[] userChoose;
        userChoose = inputHelper.split(" ");

        this.responseForEnter(userChoose);

        this.responseForGenerate(userChoose);

        this.responseForBack(userChoose);
        

        if (!(userChoose[0].equals(CategoryCommands.BACK.getCommands()) || userChoose[0].equals(CategoryCommands.ENTER.getCommands()) ||
                userChoose[0].equals(CategoryCommands.GENERATE.getCommands()))) {
            System.out.println("Wprowadź poprawną komendę \n \n \n ");
            this.showChildrenCategory();
        }
    }

    private void responseForEnter(String[] userChoose){
        //method which verification input to enter to detailed category
        if (userChoose[0].equals(CategoryCommands.ENTER.getCommands())){

            try {
                if (Integer.parseInt(userChoose[1]) > 0 && Integer.parseInt(userChoose[1]) <= this.allegroCategoryTree.get(helper).size()) {
                    choosenCategory = Integer.parseInt(userChoose[1]);
                    this.showChildrenCategory();
                } else {
                    System.out.println("Brak istniejącej kategorii do której chcesz wejść");
                    System.out.println("Spróbuj ponownie \n \n");
                    this.showChildrenCategory();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println((char) 27 + "[31mWprowadź poprawny numer kategorii" + (char) 27 +"[0m");
                this.showChildrenCategory();
            }catch (java.lang.NumberFormatException e){
                System.out.println((char) 27 + "[31mWprowadź komendę i numer kategorii po spacji" + (char) 27 +"[0m");
                this.showChildrenCategory();
            }

        }
    }

    private void responseForGenerate(String[] userChoose) {
        //method which verification input to generate link for indicated category
        try {
            if (userChoose[0].equals(CategoryCommands.GENERATE.getCommands())){

                System.out.println("Wygenerowany link dla: " + userChoose[1] + ": " + this.allegroCategoryTree.get(helper).get(Integer.parseInt(userChoose[1])-1).getName());
                String phraseInLink = this.allegroCategoryTree.get(helper).get(Integer.parseInt(userChoose[1])-1).getName();
                phraseInLink = phraseInLink.replace('ą', 'a')
                        .replace('ć', 'c').replace('ę','e')
                        .replace('ł','l').replace('n','n')
                        .replace('ó','o').replace('ś','s')
                        .replace('ź','z').replace('ż','z')
                        .replace(" ", "-");
                System.out.println("https://allegro.pl/kategoria/" +phraseInLink+ "-" + this.allegroCategoryTree.get(helper).get(Integer.parseInt(userChoose[1])-1).getCatID());
                //generuj link
            }
        } catch (java.lang.IndexOutOfBoundsException e){
            System.out.println("Błędny numer kategorii, spróbuj ponownie \n");
            this.showChildrenCategory();
        } catch (java.lang.NumberFormatException e){
            System.out.println((char) 27 + "[31mWprowadź komendę i numer kategorii po spacji" + (char) 27 +"[0m");
            this.showChildrenCategory();
        }

    }

    private void responseForBack(String[] userChoose) {
        //method which verification input to back to previous category or menu
        try {
            if (userChoose[0].equals(CategoryCommands.BACK.getCommands())) {
                if (choosenCategoryHistory.size() == 1) {
                    this.helper = 0;
                    this.choosenCategory = 0;
                    this.choosenCategoryHistory.clear();
                    this.showChildrenCategory();
                } else if (choosenCategoryHistory.isEmpty()) {
                    System.out.println("Przechodzisz do głównego menu");
                } else {
                    helper = choosenCategoryHistory.get(choosenCategoryHistory.size() - 2);
                    choosenCategoryHistory.remove(choosenCategoryHistory.size() - 1);
                    this.choosenCategory = 0;
                    this.showChildrenCategory();
                }
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("Spróbuj ponownie");
        }
    }

    private void showAllOptions(){
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
