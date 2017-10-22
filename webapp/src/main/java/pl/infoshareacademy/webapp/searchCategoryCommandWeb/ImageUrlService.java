package pl.infoshareacademy.webapp.searchCategoryCommandWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Singleton;
import java.io.IOException;

@Singleton
public class ImageUrlService {

    private static final Logger logger = LogManager.getLogger(ImageUrlService.class);

    private final FileConfiguration fileConfiguration;

    public ImageUrlService() {
        fileConfiguration = loadImageFile();
    }

    public String getImageUrl(int catID) {
        for (CategoryPicture picture : fileConfiguration.getPictureList()) {
            if (picture.getId() == catID) {
                logger.debug("Returned ImageUrlService: " + picture.getUrl());
                return picture.getUrl();
            }
        }
        logger.warn("Image not found for id: " + catID);
        return "";
    }

    private FileConfiguration loadImageFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.reader(FileConfiguration.class).readValue(ImageUrlService.class.getResourceAsStream("/images.json"));
        } catch (IOException e) {
            logger.error("caught an exception during loading ImageFile", e);
            throw new RuntimeException(e);
        }
    }
}