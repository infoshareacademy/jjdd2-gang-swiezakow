package pl.infoshareacademy;

import java.util.Scanner;

public class Main {

    public static final String KOMENDA_WYJSCIA = "exit";

    public static void main(String[] args) {

        System.out.println("\n~~wspanialy pomagacz w zakupach internetowych~~");
        System.out.println("-----------------------------------------------\n");
        printMenu();
        Scanner odczyt = new Scanner(System.in);
        String linia;
        while ( !(linia = odczyt.nextLine()).equals(KOMENDA_WYJSCIA) ) {

            switch (linia) {
                case ("1"):

                    SearchByQuestionsCommand searchByQuestionsCommand = new SearchByQuestionsCommand();
                    searchByQuestionsCommand.run();
                    // wejscie do polecenia 1
                    break;
                case ("2"):
                    SearchCategoryCommand newSearch = new SearchCategoryCommand();
                    newSearch.handleCommand(odczyt);
                    break;
                case ("3"):

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
        System.out.println("1. Polecenie nr 1");
        System.out.println("2. Szukaj produktu");
        System.out.println("3. Polecenie nr 3");
        System.out.println("4. Asystent Allegro");
        System.out.println();
        System.out.println("Aby wyjść wpisz: exit");
        System.out.println();
        System.out.println("Podaj numer polecenia: ");

    }
}
