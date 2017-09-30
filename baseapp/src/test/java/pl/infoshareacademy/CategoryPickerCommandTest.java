package pl.infoshareacademy;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryPickerCommandTest {

    public final String FILENAME = "test.xml";
    CategoryPickerCommand categoryPickerCommand = new CategoryPickerCommand(FILENAME);

    public CategoryPickerCommandTest() throws IOException, SAXException, ParserConfigurationException {
    }

    @Test
    public void childrenContainObject() {

        List<AllegroCategory> allegroCategories = categoryPickerCommand.showChildrenCategory(0);
        AllegroCategory allegroCategory = new AllegroCategory(64477, "Biuro i Reklama", 0, 2);
        assertTrue(allegroCategory.equals(allegroCategories.get(2)));
    }

    @Test
    public void childrenNotContainObject() {
        List<AllegroCategory> allegroCategories = categoryPickerCommand.showChildrenCategory(999999);
        assertTrue(allegroCategories.isEmpty());
    }

    @Test
    public void checkLinkGenerator() {
        List<AllegroCategory> allegroCategories = categoryPickerCommand.showChildrenCategory(0);
        String linkTest = categoryPickerCommand.generateLink(allegroCategories.get(1));
        String link = "https://allegro.pl/kategoria/Bilety-98553";
        assertTrue(link.equals(linkTest));
    }

    @Test
    public void checkLinkGeneratorNull() {
        List<AllegroCategory> allegroCategories = categoryPickerCommand.showChildrenCategory(0);
        AllegroCategory fakeAllegroCategory = null;
        String linkTest = categoryPickerCommand.generateLink(fakeAllegroCategory);
        String link = "Błąd";
        assertTrue(link.equals(linkTest));
    }
}