package pl.infoshareacademy;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class CategoryPickerCommandTest {

    private final String FILENAME = "test.xml";
    private CategoryPickerCommand categoryPickerCommand;

    @Before
    public void setUp() throws Exception {
        ConfigurationLoader.loadConfiguration();
        categoryPickerCommand = new CategoryPickerCommand(CategoryPickerCommand.class.getResource("/" + FILENAME).getPath());
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
        AllegroCategory fakeAllegroCategory = null;
        String linkTest = categoryPickerCommand.generateLink(fakeAllegroCategory);
        String link = "Błąd";
        assertTrue(link.equals(linkTest));
    }
}