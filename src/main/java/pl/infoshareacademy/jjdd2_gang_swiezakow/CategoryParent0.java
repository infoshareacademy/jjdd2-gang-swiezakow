package pl.infoshareacademy.jjdd2_gang_swiezakow;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class CategoryParent0 {


    public void categoryParent0Shower() throws IOException, SAXException, ParserConfigurationException {
    AllegroCategoryLoader loader = new AllegroCategoryLoader();
        Map<Integer, List<AllegroCategory>> categoryTreeParent0 = (HashMap<Integer, List<AllegroCategory>>) loader.loadCategoryTree();
        Set<Map.Entry<Integer, List<AllegroCategory>>> entries = categoryTreeParent0.entrySet();

        CollectionPrinter.print(categoryTreeParent0.get(0));
        Scanner odczyt1 = new Scanner(System.in);
        System.out.println("Wybierz kategorię: ");
        Integer categoryNumber = odczyt1.nextInt();




//        for (Map.Entry<Integer, List<AllegroCategory>> categoryEntries :
//                entries) {
//            System.out.println(". " + categoryEntries.getKey().);
//        }

//        for (int i = 0; i<lista.size(); i++){
//            if (lista.get(i).getParent() == 0) {
//                AllegroCategory category = lista.get(i);
//                System.out.println(liczydlo +  ". " +category.getName());
//                liczydlo++;
//            }
//        }
    }
}

// Wykonane wspolnie: Bodzio i Michal
