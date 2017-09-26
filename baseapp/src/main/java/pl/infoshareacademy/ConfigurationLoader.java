package pl.infoshareacademy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ConfigurationLoader {

    private static Configuration configuration;

    public static void loadConfiguration() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            configuration = objectMapper.reader(Configuration.class).readValue(
                    Configuration.class.getResourceAsStream("/config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
