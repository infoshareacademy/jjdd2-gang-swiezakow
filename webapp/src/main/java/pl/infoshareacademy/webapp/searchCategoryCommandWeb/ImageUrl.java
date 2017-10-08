package pl.infoshareacademy.webapp.searchCategoryCommandWeb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ImageUrl {
    private static final Logger logger = LogManager.getLogger(ImageUrl.class);
    public String getImageUrl(List<CategoryPicture> picturesList, int catID) {
        for (CategoryPicture picture : picturesList) {
            if (picture.getId() == catID) {
                logger.debug("Returned ImageUrl: " + picture.getUrl());
                return picture.getUrl();
            }
        }
        logger.warn("Image not found for id: " + catID);
        return "";
    }
}
