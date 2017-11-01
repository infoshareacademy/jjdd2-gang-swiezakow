package pl.infoshareacademy.reportService;

import pl.infoshareacademy.reportService.ModelsStore.RecipientModel;

import javax.ejb.Schedule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;


public class UpdateRESTTimerThread {

    private static final String ENDPOINT = "http://localhost:8080/report-rest-server/";

    @Schedule(second="*/10", minute="*",hour="*", persistent=false)
    public void doWork(){
        System.out.println("timer: " + Instant.now());
    }

    public String getLastUpdateDatas() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(ENDPOINT + "last-update-data-date");
        Response response = webTarget.request().get();
        String restResponse = response.readEntity(String.class);
        response.close();

        return restResponse;
    }

    public ArrayList<RecipientModel> getActualTasks() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(ENDPOINT + "activetasks");
        Response response = webTarget.request().get();
        RecipientModel[] restResponse = response.readEntity(RecipientModel[].class);
        response.close();
        ArrayList<RecipientModel> result = new ArrayList<>(Arrays.asList(restResponse));
        return result;
    }

    public void updateDataInReportModule(String dataStore) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(ENDPOINT + "updatedatas");
        webTarget.request().post(Entity.entity(dataStore, MediaType.APPLICATION_JSON_TYPE));
    }

    public void addTaskInReportModule(RecipientModel recipientModel) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(ENDPOINT + "addtask");
        webTarget.request().post(Entity.entity(recipientModel, MediaType.APPLICATION_JSON_TYPE));
    }
    
}
