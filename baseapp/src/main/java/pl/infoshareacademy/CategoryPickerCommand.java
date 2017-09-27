package pl.infoshareacademy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryPickerCommand {
    AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();
    static final String FILENAME = "C:\\Users\\bogum\\Documents\\git\\jjdd2-gang-swiezakow\\baseapp\\src\\main\\resources\\Allegro_cathegories_2016-02-13.xml";
    Map<Integer, List<AllegroCategory>> allegroCategoryTree = allegroCategoryLoader.loadCategoryTree(FILENAME);

    public CategoryPickerCommand() throws ParserConfigurationException, SAXException, IOException {
    }

    public List<AllegroCategory> showChildrenCategory(Integer selectedCategory) {
        try {
            if (allegroCategoryTree.get(selectedCategory) == null) {
                return new ArrayList<>();
            } else {
                return allegroCategoryTree.get(selectedCategory);
            }
        } catch (java.lang.IndexOutOfBoundsException e){
            return new  ArrayList<>();
        }
    }

    public String generateLink(AllegroCategory allegroCategory) {

        if (allegroCategory == null) {
            return "Błąd";
        } else {
            String phraseInLink = allegroCategory.getName()
                    .replace('ć', 'c').replace('ę', 'e')
                    .replace('ł', 'l').replace('n', 'n')
                    .replace('ó', 'o').replace('ś', 's')
                    .replace('ź', 'z').replace('ż', 'z')
                    .replace(" ", "-");

            return ("https://allegro.pl/kategoria/" + phraseInLink + "-" + allegroCategory.getCatID());
        }
    }

}