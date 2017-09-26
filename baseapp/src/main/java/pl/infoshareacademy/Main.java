package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final String KOMENDA_WYJSCIA = "exit";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ConfigurationLoader.loadConfiguration();

        Configuration config = ConfigurationLoader.getConfiguration();

        System.setProperty("log4j.configurationFile", "log4j2.xml");
        Logger log = LogManager.getLogger(Main.class);
        log.info("Application has up-started");

        System.out.println("\n~~wspanialy pomagacz w zakupach internetowych~~");
        System.out.println("-----------------------------------------------\n");
        printMenu();
        Scanner odczyt = new Scanner(System.in);
        String linia;
        while ( !(linia = odczyt.nextLine()).equals(KOMENDA_WYJSCIA) ) {
            log.debug("User has chosen " + linia);

            switch (linia) {
                case ("1"):
                    SearchByQuestionsCommand searchByQuestionsCommand = new SearchByQuestionsCommand(config.getFilePath());
                    searchByQuestionsCommand.run();
                    break;
                case ("2"):
                    SearchCategoryCommand newSearch = new SearchCategoryCommand(config.getFilePath());
                    newSearch.handleCommand(odczyt);
                    break;
                case ("3"):
                    CategoryPickerCommand categoryPickerCommand = new CategoryPickerCommand();
                    categoryPickerCommand.showChildrenCategory();
                    break;
                case("4"):
                    SearchQueryCommand searchQueryCommand = new SearchQueryCommand(config.getFilePath());
                    searchQueryCommand.queryCommand(odczyt);
                    break;
                default:
                    log.info("The chosen number doesn't exist");

                    System.out.println("Niepoprawny numer. Podaj liczbę.");
            }
            log.debug("menu has been printed");
            printMenu();
        }
        log.info("User has entered 'exit'");
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