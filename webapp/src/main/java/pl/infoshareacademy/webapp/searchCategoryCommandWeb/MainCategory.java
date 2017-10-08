package pl.infoshareacademy.webapp.searchCategoryCommandWeb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.AllegroCategory;

import java.util.List;
import java.util.Optional;

public class MainCategory {
    private static final Logger logger = LogManager.getLogger(MainCategory.class);
    public Optional<AllegroCategory> getMainCategory(AllegroCategory categoryResult, List<AllegroCategory> allCategories) {
        if (categoryResult.getParent() == 0) {
            logger.info("MainCategory was found");
            return Optional.of(categoryResult);
        } else {
            int parent = categoryResult.getParent();
            for (AllegroCategory allCategory : allCategories) {
                if (allCategory.getCatID() == parent) {
                    return getMainCategory(allCategory, allCategories);
                }
            }
        }
        logger.warn("Allegro category not found for category id: " + categoryResult.getCatID() );
        return Optional.empty();
    }
}
