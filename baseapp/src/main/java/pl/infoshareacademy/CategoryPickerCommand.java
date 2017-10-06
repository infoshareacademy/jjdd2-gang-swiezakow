package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryPickerCommand {
    private static final Logger logger = LogManager.getLogger(CategoryPickerCommand.class);

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
                logger.warn("can not found parent's category: " + selectedCategory);
                return new ArrayList<>();
            }
            logger.info("return list of selected category: " + selectedCategory);
            return allegroCategoryTree.get(selectedCategory);
        } catch (java.lang.IndexOutOfBoundsException e) {
            logger.warn("index out of bounds exception");
            return new ArrayList<>();
        }
    }

    public String generateLink(AllegroCategory allegroCategory) {

        if (allegroCategory == null) {
            logger.warn("generating link for no existing category");
            return "Błąd";
        } else {
            AllegroLink allegroLink = new AllegroLink();
            String phraseInLink = allegroLink.makeLink(allegroCategory.getName(), allegroCategory.getCatID());
            logger.info("successfully generated link for: " + allegroCategory.getName());
            return phraseInLink;
        }
    }

}