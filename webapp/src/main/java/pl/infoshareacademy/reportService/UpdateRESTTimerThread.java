package pl.infoshareacademy.reportService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.Configuration;
import pl.infoshareacademy.ConfigurationLoader;
import pl.infoshareacademy.reportService.DataFactory.DataProcessingService;
import pl.infoshareacademy.reportService.ModelsStore.DataStore;
import pl.infoshareacademy.reportService.ModelsStore.RecipientModel;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Singleton
public class UpdateRESTTimerThread {

    private final static Logger logger = LogManager.getLogger(UpdateRESTTimerThread.class);

    private final Configuration config = ConfigurationLoader.getConfiguration();

    private final String ENDPOINT;

    public UpdateRESTTimerThread() {
        ENDPOINT = config.getRestURL();
    }


    @Inject
    StatisticsResultsBean statisticsResultsBean;

    @Schedule(minute="*/1",hour="*", persistent=false)
    public void updateDateInReportModulePeriodically(){
        DataProcessingService dataProcessingService = new DataProcessingService(statisticsResultsBean);
        DataStore newSet = dataProcessingService.getNewDataSetForDB();
        String newSetToJSON = dataProcessingService.processingDataFromDB(newSet);
        if (newSetToJSON.equals("Problem")) {
            logger.fatal("Problem with synchronize report-rest-server !!!");
            return;
        }
        logger.info("trying update data in report-rest-server");
        updateDataInReportModule(newSetToJSON);
    }

    public String getLastUpdateDate() {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(ENDPOINT + "last-update-data-date");
            Response response = webTarget.request().get();
            String restResponse = response.readEntity(String.class);
            response.close();
            logger.info("successfully check last update data date in report-rest-server");
            return restResponse;
        } catch (javax.ws.rs.ProcessingException e) {
            logger.warn("could not check last update data date in report-rest-server");
            logger.fatal("could not connect to report-rest-server !!!" + e);
            return "Problem";
        }
    }

    public Optional<ArrayList<RecipientModel>> getActualTasks() {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(ENDPOINT + "activetasks");
            Response response = webTarget.request().get();
            RecipientModel[] restResponse = response.readEntity(RecipientModel[].class);
            response.close();
            ArrayList<RecipientModel> result = new ArrayList<>(Arrays.asList(restResponse));
            return Optional.ofNullable(result);
        } catch (javax.ws.rs.ProcessingException e) {
            logger.fatal("could not get actual tasks in report-rest-server");
            return Optional.empty();
        }
    }

    public void updateDataInReportModule(String dataStore) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(ENDPOINT + "updatedatas");
        webTarget.request().post(Entity.entity(dataStore, MediaType.APPLICATION_JSON_TYPE));
        logger.info("Sent newDataSet to report-rest-server");
    }

    public void addTaskInReportModule(RecipientModel recipientModel) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(ENDPOINT + "addtask");
        webTarget.request().post(Entity.entity(recipientModel, MediaType.APPLICATION_JSON_TYPE));
        logger.info("Sent new task to report-rest-server");
    }

    public void deleteTask(Integer id) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(ENDPOINT + "deletetask/" + id);
        webTarget.request().delete();
    }

}
