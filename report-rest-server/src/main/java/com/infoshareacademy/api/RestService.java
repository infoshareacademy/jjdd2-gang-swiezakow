package com.infoshareacademy.api;

import com.infoshareacademy.model.RecipientModel;
import com.infoshareacademy.model.TasksStore;
import com.infoshareacademy.model.databaseinputs.DataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

@Path("/")
public class RestService {
    private Logger LOG = LoggerFactory.getLogger(RestService.class);

    @Context
    private UriInfo uriInfo;

    @Inject
    private TasksStore tasksStore;

    @Inject
    private DataStore dateStore;

    @GET
    @Path("/activetasks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActiveTasks() {

        Map<Integer, RecipientModel> tasksStoreBase = tasksStore.getBase();

        if (tasksStoreBase.isEmpty()) {
            LOG.info("showed empty TasksStore");
            return Response.ok().build();
        }

        LOG.info("showed TasksStore of number active tasks: " + tasksStore.getBase().size());
        return Response.ok(tasksStoreBase.values()).build();
    }

    @POST
    @Path("/addtask")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTask(RecipientModel recipientModel) {

        tasksStore.add(new RecipientModel(recipientModel.getEmails(),
                recipientModel.getSendTimeDate(),
                recipientModel.getInterval(),
                tasksStore.findNewId()));

        return getActiveTasks();
    }

    @GET
    @Path("/actualstatisticsstore")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStats() {
        return Response.ok(dateStore.getStatisticsStore()).build();
    }


    @POST
    @Path("/updatedatas")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateDateStore(DataStore dataStore) {
        dateStore.setStatisticsStore(dataStore.getStatisticsStore());
    }

    @GET
    @Path("/last-update-data-date")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLastUpdateDataDate() {
      return Response.ok(dateStore.getStatisticsStore().getUpdateDate()).build();
    }

    @DELETE
    @Path("/deletetask/{task}")
    public void deleteTask(@PathParam("task") String id) {
        if (tasksStore.getBase().containsKey(String.valueOf(id))) {
            tasksStore.getBase().remove(id);
        }
    }

}
