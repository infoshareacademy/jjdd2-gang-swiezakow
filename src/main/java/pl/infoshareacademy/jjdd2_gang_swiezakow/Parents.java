/**
package pl.infoshareacademy.jjdd2_gang_swiezakow;

import java.util.List;

public class Parents {
    public static void parents {

        AllegroCategoryLoader loader = new AllegroCategoryLoader();
        List<AllegroCategory> lista = loader.loadAllCategories();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getParent() == 0) {
                AllegroCategory category = lista.get(i);
                System.out.println(category.getName());
            }

        }
    }
}
**/