package pl.infoshareacademy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class Main {

    public static final String KOMENDA_WYJSCIA = "exit";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {


        AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();

        HashMap<Integer, List<AllegroCategory>> allegroCategoryTree = (HashMap<Integer, List<AllegroCategory>>) allegroCategoryLoader.loadCategoryTree();


        Set<Map.Entry<Integer, List<AllegroCategory>>> entries = allegroCategoryTree.entrySet();

//        for (Map.Entry<Integer, List<AllegroCategory>> categoryEntry :
//                entries) {
//            System.out.println("Key: " + categoryEntry.getKey() + "\nValue: \n" + categoryEntry.getValue());
//        }


        ArrayList<AllegroCategory> allegroCategoryArrayList = new ArrayList<>();
        Scanner odczyt = new Scanner(System.in);
        Integer levelOfCategory;

        ArrayList<Integer> arrayListChildrenCategories = new ArrayList<>();


        for (int i = 0; i < allegroCategoryTree.get(0).size(); i++) {
            arrayListChildrenCategories.add(allegroCategoryTree.get(0).get(i).getCatID());
            System.out.println((allegroCategoryTree.get(0).get(i).getCatPosition()+1) + ". " + allegroCategoryTree.get(0).get(i).toString() +"                    " + allegroCategoryTree.get(0).get(i).getCatID());
        }

        Integer helper = 1;
        Integer choosenCategory = odczyt.nextInt();
        for (int i = 0; i < allegroCategoryTree.get(0).size(); i++) {
            if (choosenCategory == (allegroCategoryTree.get(0).get(i).getCatPosition() + 1)) {
                helper = allegroCategoryTree.get(0).get(i).getCatID();
            }

        }

        for (int i = 0; i < allegroCategoryTree.get(helper).size(); i++) {
                arrayListChildrenCategories.add(allegroCategoryTree.get(helper).get(i).getCatID());
                System.out.println((allegroCategoryTree.get(helper).get(i).getCatPosition() + 1) + ". " + allegroCategoryTree.get(helper).get(i).toString() + "                    " + allegroCategoryTree.get(helper).get(i).getCatID());
        }


//        while (true) {
//            levelOfCategory = odczyt.nextInt();
//            try {
//                for (int i = 0; i < allegroCategoryTree.get(levelOfCategory).size(); i++) {
//                    System.out.println(allegroCategoryTree.get(levelOfCategory).get(i).getCatID() + ". " + allegroCategoryTree.get(levelOfCategory).get(i).toString());
//                }
//            } catch (java.lang.NullPointerException e) {
//                System.out.println("Koniec podkategorii");
//            }
//
//        }
//        int[] helpTable = int[allegroCategoryTree.get(levelOfCategory).size()]

//        System.out.println(entries.size());




//        for (AllegroCategory allegroCategory :
//                allegroCategoryTree){
//            System.out.println(allegroCategoryTree.get(levelOfCategory).get(allegroCategory).toString());
//        }
//        System.out.println(allegroCategoryTree.get(0).get(0).toString());



        //Iterator<AllegroCategory> iterator = allegroCategoryLoa.iterator();

//        System.out.println("\n~~wspanialy pomagacz w zakupach internetowych~~");
//        System.out.println("-----------------------------------------------\n");
//        System.out.println("1. Polecenie nr 1");
//        System.out.println("2. Polecenie nr 2");
//        System.out.println("3. Polecenie nr 3");
//        System.out.println("4. Polecenie nr 4");
//        System.out.println();
//        System.out.println("Aby wyjść wybierz: exit");
//        System.out.println();
//        System.out.println("Podaj numer polecenia: ");
//
//        Scanner odczyt = new Scanner(System.in);
//        String linia;
//        while ( !(linia = odczyt.nextLine()).equals(KOMENDA_WYJSCIA) ) {
//
//            switch (linia) {
//                case ("1"):
//                    SearchByQuestionsCommand searchByQuestionsCommand = new SearchByQuestionsCommand();
//                    //searchByQuestionsCommand.//categorySearchByQuestionCommand
//                    // wejscie do polecenia 1
//                    break;
//                case ("2"):
//                    SearchCategoryCommand newSearch = new SearchCategoryCommand();
//                    newSearch.handleCommand(odczyt);
//                    // wejście do polcenia 2
//                    break;
//                case ("3"):
//                    CategoryPickerCommand categoryPickerCommand = new CategoryPickerCommand();
//                    //categoryPickerCommand.//categoryPickerCammandMethod
//                    // wejście do polecenia 3
//                    break;
//                case("4"):
//
//                    // wejście do polecenia 4
//                    break;
//                case ("5"):
//                    SearchQueryCommand searchQueryCommand = new SearchQueryCommand();
//                    //searchByQuestionsCommand.//categoryQueryCommandMethod
//                    break;
//                    // wejście do polecenia 5
//                default:
//                    System.out.println("Niepoprawny numer. Podaj liczbę.");
//            }
        }
    }

