package pl.infoshareacademy.webapp;

import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroCategoryLoader;

import javax.ejb.Singleton;
import java.util.List;
import java.util.Map;

@Singleton
public class AllegroCategoryService {
    private AllegroCategoryLoader loader = new AllegroCategoryLoader();
    private Map<Integer, List<AllegroCategory>> categoriesTree;

    public AllegroCategoryService() {
        String filePath = System.getProperty("java.io.tmpdir") + "/file.xml";
        categoriesTree = loader.loadCategoryTree(filePath);
    }

    public List<AllegroCategory> getAllegroCategoriesForParent(Integer catId) {
        return categoriesTree.get(catId);
    }
}
