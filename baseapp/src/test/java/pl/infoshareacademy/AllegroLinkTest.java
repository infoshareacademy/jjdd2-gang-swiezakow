package pl.infoshareacademy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AllegroLinkTest {

    @Before
    public void setUp() throws Exception {
        ConfigurationLoader.loadConfiguration();
    }

    @Test
    public void testAllegroLink() {
        String name = "Przykładowa nazwa";
        int id = 10;

        String result = AllegroLink.makeLink(name, id);

        Assert.assertEquals("https://allegro.pl/kategoria/Przykadowa-nazwa-10", result);
    }

    @Test
    public void testAllegroLinkWithEmptyString() {
        String name = "";
        int id = 1;

        String result = AllegroLink.makeLink(name, id);

        Assert.assertEquals("https://allegro.pl/kategoria/-1", result);
    }
}
