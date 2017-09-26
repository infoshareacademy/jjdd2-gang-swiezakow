package pl.infoshareacademy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;

public class AllegroCategoryLoaderTest  {
private static String FILENAME = "test.xml";
private static String FILENAME2 = "empty.xml";



    private List<AllegroCategory> test1() {      // stworzenie listy
    AllegroCategory allegroCategory = new AllegroCategory(26013, "Antyki i Sztuka", 0, 0);  // dodadnie  1 obiektu
    AllegroCategory allegroCategory2 = new AllegroCategory(98553, "Bilety", 0, 1);
    AllegroCategory allegroCategory3 = new AllegroCategory(64477, "Biuro i Reklama", 0, 2);
    List<AllegroCategory> lista = Arrays.asList(allegroCategory, allegroCategory2, allegroCategory3);        //dodanie do listy 3 obiektow
    return lista;

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

        Assert.assertEquals(list, result);

    }
    @Test
    public void emptyFile() {
        URL url = getClass().getResource("/"+FILENAME2);
        System.out.println(url.getPath());
        AllegroCategoryLoader allegroCategoryLoader = new AllegroCategoryLoader();

        List<AllegroCategory> result = allegroCategoryLoader.loadAllCategories(url.getPath());
        List<AllegroCategory> list = new ArrayList<>();
        Assert.assertEquals(list, result);


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


        Assert.assertEquals( mapa1(), resulat);






}

}




