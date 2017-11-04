package pl.infoshareacademy.webapp.authorisation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Singleton;
import java.io.IOException;

@Singleton
public class AdminService {
    private static final Logger logger = LogManager.getLogger(AdminService.class);

    private final AdminConfiguration adminConfiguration;

    public AdminService() {
        adminConfiguration = loadAdminFile();
    }

    public AdminConfiguration getAdminConfiguration() {
        return adminConfiguration;
    }

    public boolean isAdmin(String email) {
        return adminConfiguration.getAdmins().contains(email);
    }

    private AdminConfiguration loadAdminFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.reader(AdminConfiguration.class).readValue(AdminService.class.getResourceAsStream("/admin.json"));
        } catch (IOException e) {
            logger.error("caught an exception during loading AdminFile", e);
            throw new RuntimeException(e);
        }
    }
}
