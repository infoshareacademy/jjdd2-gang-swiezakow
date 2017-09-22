package pl.infoshareacademy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.StringReader;
import java.net.URL;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Test
    public void expectedNoFile() throws Exception {

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

}