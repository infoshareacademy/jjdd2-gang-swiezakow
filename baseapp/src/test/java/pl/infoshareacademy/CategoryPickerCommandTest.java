package pl.infoshareacademy;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryPickerCommandTest {
    CategoryPickerCommand categoryPickerCommand = new CategoryPickerCommand();

    public CategoryPickerCommandTest() throws IOException, SAXException, ParserConfigurationException {
    }

    @Test
    public void childrenContainObject() {

        List<AllegroCategory> allegroCategories = categoryPickerCommand.showChildrenCategory(0);
        AllegroCategory allegroCategory = new AllegroCategory(64477, "Biuro i Reklama", 0, 2);
        Assert.assertTrue(allegroCategory.equals(allegroCategories.get(2)));
    }

    @Test
    public void childrenNotContainObject() {
        List<AllegroCategory> allegroCategories = categoryPickerCommand.showChildrenCategory(999999);
        Assert.assertTrue(allegroCategories.isEmpty());
    }

    @Test
    public void checkLinkGenerator() {
        List<AllegroCategory> allegroCategories = categoryPickerCommand.showChildrenCategory(0);
        String linkTest = categoryPickerCommand.generateLink(allegroCategories.get(3));
        String link = "https://allegro.pl/kategoria/"
        Assert.assertEquals();
    }

}
