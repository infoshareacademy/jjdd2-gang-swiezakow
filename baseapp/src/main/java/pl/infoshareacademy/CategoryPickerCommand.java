package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CategoryPickerCommand {

    private static final Logger logger = LogManager.getLogger(CategoryPickerCommand.class);

    private Configuration config = ConfigurationLoader.getConfiguration();

    private AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();
    private final String filename;
    private HashMap<Integer, List<AllegroCategory>> allegroCategoryTree;
    private ArrayList<Integer> choosenCategoryHistory = new ArrayList<>();
    private Scanner inputReader = new Scanner(System.in);

    private Integer choosenCategory = 0;
    private Integer helper = 0;

    private Integer getChoosenCategory() {
        return choosenCategory;
    }

    private void setChoosenCategory(Integer choosenCategory) {
        this.choosenCategory = choosenCategory;
    }

    private Integer getHelper() {
        return helper;
    }

    private void setHelper(Integer helper) {
        this.helper = helper;
    }

    public CategoryPickerCommand(String filename) throws ParserConfigurationException, SAXException, IOException {
        this.filename = filename;
        allegroCategoryTree = (HashMap<Integer, List<AllegroCategory>>) allegroCategoryLoader.loadCategoryTree(filename);
    }

    public void showChildrenCategory() {
        try {
            this.showAllOptions();


            for (int i = 0; i < allegroCategoryTree.get(getHelper()).size(); i++) {
                if (getChoosenCategory() == (allegroCategoryTree.get(getHelper()).get(i).getCatPosition() + 1)) {
                    setHelper(allegroCategoryTree.get(getHelper()).get(i).getCatID());
                    choosenCategoryHistory.add(getHelper());
                }

            }

            for (int i = 0; i < allegroCategoryTree.get(getHelper()).size(); i++) {
                System.out.println((allegroCategoryTree.get(getHelper()).get(i).getCatPosition() + 1) + ". " + allegroCategoryTree.get(getHelper()).get(i).toString());
            }
            System.out.println("Co chcesz zrobic?");

            this.veryficationUserInput();

        } catch (java.lang.NullPointerException e) {
            logger.error("caught an exception during showing children category", e);
            System.out.println((char)27 +"[31mBrak podkategorii, możesz wygenerować link lub się cofnąć" + (char)27 +"[0m ");
            setChoosenCategory(0);
            setHelper(this.choosenCategoryHistory.get(this.choosenCategoryHistory.size() - 2));
            this.choosenCategoryHistory.remove(this.choosenCategoryHistory.size()-1);
            this.showChildrenCategory();
        }

    }
    private void veryficationUserInput() {
        System.out.println(this.choosenCategoryHistory);
        String inputHelper = inputReader.nextLine();
        logger.info("User has entered " + inputHelper);
        String[] userChoose;
        userChoose = inputHelper.split(" ");

        this.responseForEnter(userChoose);

        this.responseForGenerate(userChoose);

        this.responseForBack(userChoose);
        

        if (!(userChoose[0].equals(CategoryCommands.BACK.getCommands()) || userChoose[0].equals(CategoryCommands.ENTER.getCommands()) ||
                userChoose[0].equals(CategoryCommands.GENERATE.getCommands()))) {
            logger.warn("User has entered wrong answer");
            System.out.println("Wprowadź poprawną komendę \n \n \n ");
            this.showChildrenCategory();
        }
    }

    private void responseForEnter(String[] userChoose) {
        if (userChoose[0].equals(CategoryCommands.ENTER.getCommands())){

            try {
                if (Integer.parseInt(userChoose[1]) > 0 && Integer.parseInt(userChoose[1]) <= this.allegroCategoryTree.get(getHelper()).size()) {
                    setChoosenCategory(Integer.parseInt(userChoose[1]));
                    this.showChildrenCategory();
                } else {
                    logger.warn("Category doesn't exist");
                    System.out.println("Brak istniejącej kategorii do której chcesz wejść");
                    System.out.println("Spróbuj ponownie \n \n");
                    this.showChildrenCategory();
                }
            } catch (IndexOutOfBoundsException e) {
                logger.error("caught an exception during response for enter", e);
                System.out.println((char) 27 + "[31mWprowadź poprawny numer kategorii" + (char) 27 +"[0m");
                this.showChildrenCategory();
            }catch (java.lang.NumberFormatException e){
                logger.error("caught an exception during response for enter", e);
                System.out.println((char) 27 + "[31mWprowadź komendę i numer kategorii po spacji" + (char) 27 +"[0m");
                this.showChildrenCategory();
            }

        }
    }

    private void responseForGenerate(String[] userChoose) {
        try {
            if (userChoose[0].equals(CategoryCommands.GENERATE.getCommands())){

                System.out.println("Wygenerowany link dla: " + userChoose[1] + ": " + this.allegroCategoryTree.get(getHelper()).get(Integer.parseInt(userChoose[1])-1).getName());
                String phraseInLink = this.allegroCategoryTree.get(getHelper()).get(Integer.parseInt(userChoose[1])-1).getName();
                phraseInLink = phraseInLink.replace('ą', 'a')
                        .replace('ć', 'c').replace('ę','e')
                        .replace('ł','l').replace('n','n')
                        .replace('ó','o').replace('ś','s')
                        .replace('ź','z').replace('ż','z')
                        .replace(" ", "-");
                String link = String.format(config.getLinkForCPC(), phraseInLink, this.allegroCategoryTree.get(getHelper()).get(Integer.parseInt(userChoose[1])-1).getCatID());
                logger.info("returned link: " + link);
                System.out.println(link);
            }
        } catch (java.lang.IndexOutOfBoundsException e){
            logger.error("caught an exception during response for generate", e);
            System.out.println("Błędny numer kategorii, spróbuj ponownie \n");
            this.showChildrenCategory();
        } catch (java.lang.NumberFormatException e){
            logger.error("caught an exception during response for generate", e);
            System.out.println((char) 27 + "[31mWprowadź komendę i numer kategorii po spacji" + (char) 27 +"[0m");
            this.showChildrenCategory();
        }

    }

    private void responseForBack(String[] userChoose) {
        //method which verification input to back to previous category or menu
        try {
            if (userChoose[0].equals(CategoryCommands.BACK.getCommands())) {
                if (choosenCategoryHistory.size() == 1) {
                    this.setHelper(0);
                    this.setChoosenCategory(0);
                    this.choosenCategoryHistory.clear();
                    this.showChildrenCategory();
                } else if (choosenCategoryHistory.isEmpty()) {
                    System.out.println("Przechodzisz do głównego menu");
                } else {
                    setHelper(choosenCategoryHistory.get(choosenCategoryHistory.size() - 2));
                    choosenCategoryHistory.remove(choosenCategoryHistory.size() - 1);
                    this.setChoosenCategory(0);
                    this.showChildrenCategory();
                }
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e){
            logger.error("caught an exception during response for back", e);
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
