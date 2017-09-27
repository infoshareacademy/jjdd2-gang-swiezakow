package pl.infoshareacademy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class ConfigurationLoaderTest {
    private static final String FILENAME = "/right.json";
    private static final String FILENAME2 = "/wrong.json";
    private static final String FILENAME3 = "/empty.json";
    private static final String FILENAME4 = "/hahano.json";

    @Test
    public void testConfigurationLoaderWithRightFile() {
        ConfigurationLoader.loadConfiguration(FILENAME);
        Configuration result = ConfigurationLoader.getConfiguration();

        Assert.assertEquals(result.getLinkForAL(), "https://allegro.pl/kategoria/%s-%d");
    }

    @Test(expected = RuntimeException.class)
    public void testConfigurationLoaderWithWrongFile() {
        ConfigurationLoader.loadConfiguration(FILENAME2);
    }

    @Test(expected = RuntimeException.class)
    public void testConfigurationLoaderWithEmptyFile() {
        ConfigurationLoader.loadConfiguration(FILENAME3);
    }

    @Test(expected = RuntimeException.class)
    public void testConfigurationLoaderWithNoFile() {
        ConfigurationLoader.loadConfiguration(FILENAME4);
    }

    @After
    public void tearDown() throws Exception {
        ConfigurationLoader.clearConfiguration();
    }
}
