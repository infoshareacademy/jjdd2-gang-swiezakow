package pl.infoshareacademy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class SearchCategoryCommand {

    public void handleCommand(Scanner scanner) throws ParserConfigurationException, SAXException, IOException {
        System.out.println("Czego szukasz?");
        String line = scanner.nextLine();
    }
}
