package pl.infoshareacademy.webapp;

import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroCategoryLoader;

import javax.ejb.Singleton;
import java.util.List;
import java.util.Map;

@Singleton
public class AllegroCategoryService {
    private AllegroCategoryLoader loader = new AllegroCategoryLoader();

    public List<AllegroCategory> getAllegroCategoriesForParent(Integer catId) {
        String filePath = System.getProperty("java.io.tmpdir") + "/file.xml";
        Map<Integer, List<AllegroCategory>> listMap = loader.loadCategoryTree(filePath);

        return listMap.get(catId);
    }
}
