package pl.infoshareacademy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static final String KOMENDA_WYJSCIA = "exit";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        Main.showMenuSout();

        Scanner odczyt = new Scanner(System.in);
        String linia;
        while ( !(linia = odczyt.nextLine()).equals(KOMENDA_WYJSCIA) ) {

            switch (linia) {
                case ("1"):

                    // wejscie do polecenia 1
                    break;
                case ("2"):
                    SearchCategoryCommand newSearch = new SearchCategoryCommand();
                    newSearch.handleCommand(odczyt);
                    break;
                case ("3"):
                    CategoryPickerCommand categoryPickerCommand = new CategoryPickerCommand();
                    categoryPickerCommand.showChildrenCategory();
                    // wejście do polecenia 3
                    break;
                case("4"):

                    // wejście do polecenia 4
                    break;
                case ("5"):

                    // wejście do polecenia 5
                    break;
                default:
                    System.out.println("Niepoprawny numer. Podaj liczbę.");
            }
            Main.showMenuSout();
        }
    }

    private static void showMenuSout(){
        System.out.println("\n~~wspanialy pomagacz w zakupach internetowych~~");
        System.out.println("-----------------------------------------------\n");
        System.out.println("1. Polecenie nr 1");
        System.out.println("2. Szukaj produktu");
        System.out.println("3. Szukaj po kategorii");
        System.out.println("4. Polecenie nr 4");
        System.out.println();
        System.out.println("Aby wyjść wybierz: exit");
        System.out.println();
        System.out.println("Podaj numer polecenia: ");
    }
}
