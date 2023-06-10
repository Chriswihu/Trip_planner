package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.TripDto;
import dtos.UserDto;
import entities.User;
import facades.TripFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
@Path("trip")
public class TripResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final TripFacade FACADE = TripFacade.getTripFacade(EMF);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello user\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response getAllTrips() {

        List<TripDto> tripDtos = TripFacade.getAll();
        System.out.println(tripDtos);

        for (TripDto tripDto : tripDtos) {
            for (UserDto userDto : tripDto.getUsers()) {
                userDto.setUserPass("");
            }
        }

        return Response.ok().entity(GSON.toJson(tripDtos)).build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrip(String trip) {
        TripDto tripDto = GSON.fromJson(trip, TripDto.class);
        FACADE.createTrip(tripDto);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public Response deleteTrip(@PathParam("id") int id) {
        FACADE.delete(id);
        return Response.ok().build();
    }
}