package pl.infoshareacademy.webapp.searchCategoryCommandWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ImageFileParser {
    private static final Logger logger = LogManager.getLogger(ImageFileParser.class);
    public FileConfiguration loadingImageFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.reader(FileConfiguration.class).readValue(ImageFileParser.class.getResourceAsStream("/images.json"));
        } catch (IOException e) {
            logger.error("caught an exception during loading ImageFile", e);
            throw new RuntimeException(e);
        }
    }
}
