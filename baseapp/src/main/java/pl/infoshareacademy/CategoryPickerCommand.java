package pl.infoshareacademy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryPickerCommand {
    AllegroCategoryLoader loader;
    Configuration configuration;

    Map<Integer, List<AllegroCategory>> allegroCategoryTree;

    public CategoryPickerCommand(String filename) throws ParserConfigurationException, SAXException, IOException {
        this.loader = new AllegroCategoryLoader();
        allegroCategoryTree = loader.loadCategoryTree(filename);
        configuration = new Configuration();
    }

    public List<AllegroCategory> showChildrenCategory(Integer selectedCategory) {
        try {
            if (allegroCategoryTree.get(selectedCategory) == null) {
                return new ArrayList<>();
            }
            return allegroCategoryTree.get(selectedCategory);
        } catch (java.lang.IndexOutOfBoundsException e) {
            return new ArrayList<>();
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

            return String.format(configuration.getLinkForCPC(), phraseInLink, allegroCategory.getCatID());

            //return ("%d. %s -> %s\\n\", number, parentName, name" + phraseInLink + "-" + allegroCategory.getCatID());
        }
    }

}