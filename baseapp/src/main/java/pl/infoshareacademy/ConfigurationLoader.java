package pl.infoshareacademy;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class ConfigurationLoader {

    private static Configuration configuration;

    private static final String DEFAULT_CONFIG_FILE_PATH = "/config.json";
    private static final Logger logger = LogManager.getLogger(ConfigurationLoader.class);

    public static void loadConfiguration() {
        loadConfiguration(DEFAULT_CONFIG_FILE_PATH);
    }

    public static void loadConfiguration(String path) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
            configuration = objectMapper.reader(Configuration.class).readValue(
                    Configuration.class.getResourceAsStream(path));
            String reportsUri = System.getenv("REPORTS_SYSTEM_URI");
            if (reportsUri != null) {
                logger.info("loaded rest uri form environment variable: " + reportsUri);
                configuration.setRestURL("http://" + reportsUri + "/");
            }
        } catch (IOException e) {
            logger.error("caught an exception during loading file", e);
            throw new RuntimeException(e);
        }
    }

    public static Configuration getConfiguration() {
        if (configuration == null) {
            loadConfiguration();
        }
        return configuration;
    }

    public static void clearConfiguration() {
        configuration = null;
    }
}
