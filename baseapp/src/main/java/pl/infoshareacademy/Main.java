package pl.infoshareacademy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final String KOMENDA_WYJSCIA = "exit";
    private static String FILENAME = "Allegro_cathegories_2016-02-13.xml";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        System.out.println("\n~~wspanialy pomagacz w zakupach internetowych~~");
        System.out.println("-----------------------------------------------\n");
        printMenu();
        Scanner odczyt = new Scanner(System.in);
        String linia;
        while ( !(linia = odczyt.nextLine()).equals(KOMENDA_WYJSCIA) ) {

            switch (linia) {
                case ("1"):

                    SearchByQuestionsCommand searchByQuestionsCommand = new SearchByQuestionsCommand(FILENAME);
                    searchByQuestionsCommand.run();
                    // wejscie do polecenia 1
                    break;
                case ("2"):
                    SearchCategoryCommand newSearch = new SearchCategoryCommand();
                    newSearch.handleCommand(odczyt);
                    break;
                case ("3"):
                    CategoryPickerCommand categoryPickerCommand = new CategoryPickerCommand();
                    categoryPickerCommand.showMainCategories();
                    // wejście do polecenia 3
                    break;
                case("4"):
                    SearchQueryCommand searchQueryCommand = new SearchQueryCommand();
                    searchQueryCommand.queryCommand(odczyt);
                    break;
                case ("5"):

                    // wejście do polecenia 5
                    break;
                default:
                    System.out.println("Niepoprawny numer. Podaj liczbę.");
            }
            printMenu();
        }
    }
    private static void printMenu(){
        System.out.println("Menu główne:");
        System.out.println("1. Wyszukaj kategorię na podstawie serii pytań");
        System.out.println("2. Szukaj produktu");
        System.out.println("3. Szujaj produktu w kategoriach");
        System.out.println("4. Asystent Allegro");
        System.out.println();
        System.out.println("Aby wyjść wpisz: exit");
        System.out.println();
        System.out.println("Podaj numer polecenia: ");
    }
}