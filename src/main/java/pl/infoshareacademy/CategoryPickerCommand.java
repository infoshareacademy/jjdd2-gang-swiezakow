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

    Scanner odczyt = new Scanner(System.in);

    ArrayList<Integer> arrayListChildrenCategories = new ArrayList<>();



    Integer choosenCategory = 0;
    Integer helper = 0;


    public CategoryPickerCommand() throws ParserConfigurationException, SAXException, IOException {
    }

    public void showCategory0Level() {

        for (int i = 0; i < allegroCategoryTree.get(0).size(); i++) {
            arrayListChildrenCategories.add(allegroCategoryTree.get(0).get(i).getCatID());
            System.out.println((allegroCategoryTree.get(0).get(i).getCatPosition()+1) + ". " + allegroCategoryTree.get(0).get(i).toString() +"                    " + allegroCategoryTree.get(0).get(i).getCatID());
        }

        System.out.println("Co chcesz zrobic?");

        this.whatDoYouWnatToDo();



    }

    public void showChildrenCategory(){

        for (int i = 0; i < allegroCategoryTree.get(0).size(); i++) {
             if (choosenCategory == (allegroCategoryTree.get(0).get(i).getCatPosition() + 1)) {
                helper = allegroCategoryTree.get(0).get(i).getCatID();
        }

        }
        for (int i = 0; i < allegroCategoryTree.get(helper).size(); i++) {
            arrayListChildrenCategories.add(allegroCategoryTree.get(helper).get(i).getCatID());
            System.out.println((allegroCategoryTree.get(helper).get(i).getCatPosition() + 1) + ". " + allegroCategoryTree.get(helper).get(i).toString() + "                    " + allegroCategoryTree.get(helper).get(i).getCatID());
        }

        System.out.println("Co chcesz zrobic?");

        this.whatDoYouWnatToDo();

    }

    public Integer categoryMover(){
        String userInput = odczyt.nextLine();
        String[] userChoose = userInput.split(" ");


        if (userChoose[0].equals(CategoryCommands.ENTER.getCommands())){
                return Integer.parseInt(userChoose[1]);
        }


        return -1;
    }


    public void whatDoYouWnatToDo(){
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
            this.helper = this.allegroCategoryTree.get(helper).get(Integer.parseInt(userChoose[1])).getParent();
            this.choosenCategory = 1;
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
