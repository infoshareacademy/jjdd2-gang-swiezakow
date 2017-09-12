package pl.infoshareacademy.jjdd2_gang_swiezakow;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class CategoryParent0 {


    public void categoryParent0Shower() throws IOException, SAXException, ParserConfigurationException {
    AllegroCategoryLoader loader = new AllegroCategoryLoader();
    List<AllegroCategory> lista = loader.loadAllCategories();
        int liczydlo = 1;
        for (int i = 0; i<lista.size(); i++){
            if (lista.get(i).getParent() == 0) {
                AllegroCategory category = lista.get(i);
                System.out.println(liczydlo +  ". " +category.getName());
                liczydlo++;
            }
        }
    }
}

// Wykonane wspolnie: Bodzio i Michal
