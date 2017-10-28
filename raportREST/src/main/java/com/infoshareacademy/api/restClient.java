package com.infoshareacademy.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;
import java.util.Optional;

public class restClient {
    private Logger LOG = LoggerFactory.getLogger(restClient.class);

    @Context
    private UriInfo uriInfo;

    @Inject
    private UserStore userStore;

    @GET
    @Path("/user-agent")
    public Response getUserAgent(@HeaderParam("user-agent") String userAgent) {
        LOG.info("User agent: " + userAgent);
        return Response.ok(userAgent).build();
    }

    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(@PathParam("name") String name) {
        LOG.info("Saying hello to " + name + "!");

        LOG.info("Request URI: " + uriInfo.getRequestUri());
        LOG.info("Path: " + uriInfo.getPath());
        LOG.info("Path Parameters: " + uriInfo.getPathParameters());
        LOG.info("Path Segments: " + uriInfo.getPathSegments());

        return Response.ok(name).build();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        Map<Integer, User> users = userStore.getBase();

        if (users.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(users.values()).build();
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@QueryParam("id") Integer id) {
        Optional<User> user = Optional.ofNullable(userStore.getBase().get(id));

        if (user.isPresent()) {
            return Response.ok(user.get()).build();
        }

        return Response.noContent().build();
    }

    @GET
    @Path("/login")
    public Response login() {
        String html = "<form action='authenticate' method='post'>"
                + "<input type='text' name='user'/>"
                + "<input type='text' name='password'/>"
                + "<input type='Submit' value='LOGIN'/>"
                + "</form>";

        return Response.ok(html).build();
    }

    @POST
    @Path("/authenticate")
    public Response authenticate(@FormParam("user") String user,
                                 @FormParam("password") String password) {

        boolean isAuthenticated = userStore.getBase().values().stream().map(User::getCredentials)
                .anyMatch(c -> c.getUser().equals(user) && c.getPassword().equals(password));

        if (isAuthenticated) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(Credentials credentials) {

        String user = credentials.getUser();
        LOG.info("User: " + user);

        String password = credentials.getPassword();
        LOG.info("Password: " + password);

        boolean isAuthenticated = userStore.getBase().values().stream().map(User::getCredentials)
                .anyMatch(c -> c.getUser().equals(user) && c.getPassword().equals(password));

        if (isAuthenticated) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {

        int newId = userStore.findNewId();

        userStore.add(new User(
                user.getName(),
                user.getSurname(),
                newId,
                user.getCredentials()
        ));

        return getUsers();
    }

    @PUT
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {

        if (userStore.getBase().containsKey(user.getId())) {
            userStore.add(user);
            //return getUser(user.getId());
            return Response.ok(user).build();
        }

        return Response.noContent().build();
    }

    @DELETE
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@QueryParam("id") Integer id) {
        if (userStore.getBase().containsKey(id)) {
            userStore.getBase().remove(id);
            return Response.ok(userStore.getBase().values()).build();
        }

        return Response.noContent().build();
    }

}
