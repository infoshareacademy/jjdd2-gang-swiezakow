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
    public void showMainCategoryTest() {

        List<AllegroCategory> allegroCategories = categoryPickerCommand.showMainCategories();
        AllegroCategory allegroCategory = new AllegroCategory(64477, "Biuro i Reklama", 0, 2);
        Assert.assertTrue(allegroCategory.equals(allegroCategories.get(2)));
    }

}
