package pl.infoshareacademy.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.AllegroCategory;
import pl.infoshareacademy.AllegroCategoryLoader;

import javax.ejb.Singleton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Singleton
public class AllegroCategoryService {
    private static final Logger logger = LogManager.getLogger(AllegroCategoryService.class);

    private List<AllegroCategory> categories;
    private AllegroCategoryLoader loader = new AllegroCategoryLoader();
    private Map<Integer, List<AllegroCategory>> categoriesTree;

    public AllegroCategoryService() {
        init();
    }

    private void init() {
        String filePath = getFilePath();
        categoriesTree = loader.loadCategoryTree(filePath);
        categories = loader.loadAllCategories(filePath);
    }

    public String getFilePath() {
        return System.getProperty("java.io.tmpdir") + "/file.xml";
    }

    public void saveAllegroCategoryFile(InputStream inputStream) {
        OutputStream outputStreamXML = null;
        try {
            String XMLFilePath = getFilePath();

            outputStreamXML = new FileOutputStream(new File(XMLFilePath));
            int readXML;
            byte[] bytesXML = new byte[1024];
            while ((readXML = inputStream.read(bytesXML)) != -1) {
                outputStreamXML.write(bytesXML, 0, readXML);
            }
            init();

        } catch (IOException e) {
            logger.error("Can't save file", e);
            e.printStackTrace();

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStreamXML != null) {
                    outputStreamXML.close();
                }
            } catch (IOException e) {
                logger.error("Can't close stream", e);
            }
        }
    }

    public AllegroCategory getParentForCatId(int catId) {
        for (AllegroCategory category : categories) {
            if (category.getCatID() == catId) {
                return category;
            }
        }
        return null;
    }

    public String getCategoryName(int id) {
        String categoryName = "";
        for (AllegroCategory allCategory : categories) {
            if (allCategory.getCatID() == id) {
                categoryName = allCategory.getName();
            }
        }
        return categoryName;
    }

    public List<AllegroCategory> getAllegroCategoriesForParent(Integer catId) {
        return categoriesTree.get(catId);
    }
}
