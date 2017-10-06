package pl.infoshareacademy.webapp.SearchCategoryCommandWeb;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ImageFileParser {
    public FileConfiguration loadingImageFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.reader(FileConfiguration.class).readValue(ImageFileParser.class.getResourceAsStream("/images.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
