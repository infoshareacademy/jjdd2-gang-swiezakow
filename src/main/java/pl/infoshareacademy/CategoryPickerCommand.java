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

    HashMap<Integer, List<AllegroCategory>> allegroCategoryTree = (HashMap<Integer, List<AllegroCategory>>) allegroCategoryLoader.loadCategoryTree();
    ArrayList<Integer> choosenCategoryHistory = new ArrayList<>();
    Scanner odczyt = new Scanner(System.in);


    Integer choosenCategory = 0;
    Integer helper = 0;


    public CategoryPickerCommand() throws ParserConfigurationException, SAXException, IOException {
    }

    //display the list of category, started by general
    public void showChildrenCategory(){
        try {

            for (int i = 0; i < allegroCategoryTree.get(helper).size(); i++) {
                if (choosenCategory == (allegroCategoryTree.get(helper).get(i).getCatPosition() + 1)) {
                    helper = allegroCategoryTree.get(helper).get(i).getCatID();
                    choosenCategoryHistory.add(helper);
                }

            }

            for (int i = 0; i < allegroCategoryTree.get(helper).size(); i++) {
                System.out.println((allegroCategoryTree.get(helper).get(i).getCatPosition() + 1) + ". " + allegroCategoryTree.get(helper).get(i).toString());
            }

        } catch (java.lang.NullPointerException e) {
            System.out.println("Brak podkategorii");
        }
        System.out.println("Co chcesz zrobic?");

        this.whatDoYouWnatToDo();
    }

    //method which read input from user, show next category or generate link for choosen category or back to previous category
    public void whatDoYouWnatToDo(){
        System.out.println(choosenCategoryHistory);
        String what = odczyt.nextLine();
        String[] userChoose;
        userChoose = what.split(" ");

        if (userChoose[0].equals(CategoryCommands.ENTER.getCommands())){
            choosenCategory = Integer.parseInt(userChoose[1]);
            this.showChildrenCategory();
        }

        if (userChoose[0].equals(CategoryCommands.GENERATE.getCommands())){
            System.out.println("Wygenerowany link dla: " + userChoose[1] + ": " + this.allegroCategoryTree.get(helper).get(Integer.parseInt(userChoose[1])-1).getName());
            //generuj link
        }

        if (userChoose[0].equals(CategoryCommands.BACK.getCommands())){
            if (choosenCategoryHistory.size()==1){
                helper = 0;
            } else {
                this.helper = choosenCategoryHistory.get(choosenCategoryHistory.size() - 1);
                choosenCategoryHistory.remove(choosenCategoryHistory.size() - 1);
            }
            this.choosenCategory = 0;
            this.showChildrenCategory();
        }

        if (!(userChoose[0].equals(CategoryCommands.BACK.getCommands()) || userChoose[0].equals(CategoryCommands.ENTER.getCommands()) ||
                userChoose[0].equals(CategoryCommands.GENERATE.getCommands()))) {
            System.out.println("Niepoprawna komenda");
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
