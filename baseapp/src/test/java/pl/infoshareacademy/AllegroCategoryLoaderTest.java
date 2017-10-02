package pl.infoshareacademy;

import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AllegroCategoryLoaderTest  {
    private static String FILENAME = "test.xml";
    private static String FILENAME2 = "empty.xml";

    private List<AllegroCategory> test1() {
        AllegroCategory allegroCategory = new AllegroCategory(26013, "Antyki i Sztuka", 0, 0);
        AllegroCategory allegroCategory2 = new AllegroCategory(98553, "Bilety", 0, 1);
        AllegroCategory allegroCategory3 = new AllegroCategory(64477, "Biuro i Reklama", 0, 2);
        return Arrays.asList(allegroCategory, allegroCategory2, allegroCategory3);
    }
    private Map<Integer, List<AllegroCategory>> mapa1() {
        Map<Integer, List<AllegroCategory>> fakeMap = new HashMap<>();
        fakeMap.put(0, test1());
        return fakeMap;
    }
    @Test
    public void parseFile() throws Exception {
        URL url = getClass().getResource("/"+FILENAME);
        System.out.println(url.getPath());
        AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();
        List<AllegroCategory> result = allegroCategoryLoader.loadAllCategories(url.getPath());
        List<AllegroCategory> list = test1();
        assertEquals(list, result);
    }
    @Test
    public void emptyFile() {
        URL url = getClass().getResource("/"+FILENAME2);
        System.out.println(url.getPath());
        AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();
        List<AllegroCategory> result = allegroCategoryLoader.loadAllCategories(url.getPath());
        List<AllegroCategory> list = new ArrayList<>();
        assertEquals(list, result);
    }
    @Test
    public void noFile() {
        AllegroCategoryLoader allegroCategoryLoader2 = new AllegroCategoryLoader();
        List<AllegroCategory> result2 = allegroCategoryLoader2.loadAllCategories("/non/existing/path");
        assertThat(result2.size(), is(0));
    }
    @Test
    public void theSameMap() {
        URL url = getClass().getResource("/"+FILENAME);
        AllegroCategoryLoader aCL = new AllegroCategoryLoader();
        Map<Integer, List<AllegroCategory>> resulat = aCL.loadCategoryTree(url.getPath());
        assertEquals( mapa1(), resulat);
    }
}