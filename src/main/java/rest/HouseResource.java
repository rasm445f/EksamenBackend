package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HouseDto;
import dtos.UserDTO;
import entities.Role;
import entities.House;
import entities.User;
import errorhandling.API_Exception;
import facades.HouseFacade;
import facades.UserFacade;
import javassist.NotFoundException;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("house")
public class HouseResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final HouseFacade FACADE =  HouseFacade.getHouseFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() throws NotFoundException {
        return Response.ok().entity(GSON.toJson(FACADE.getAllHouses())).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHouse(@PathParam("id") int id) throws API_Exception {
        return Response.ok().entity(GSON.toJson(FACADE.getHouseById(id))).build();
    }

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String house) {
        House houseTwo =GSON.fromJson(house, House.class);
        House burner = new House(houseTwo.getAddress(), houseTwo.getCity(),houseTwo.getNumberOfRooms());
        House newHouse = FACADE.createHouse(burner);
        return Response.ok().entity(GSON.toJson(newHouse)).build();

    }
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") int id) throws EntityNotFoundException, API_Exception {
        HouseDto deleted = FACADE.deleteHouse(id);
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }

    @GET
    @Path("/tenant/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBoatByHarborID(@PathParam("id") int id) throws NotFoundException, API_Exception {
        return Response.ok().entity(GSON.toJson(FACADE.getAllTenantsFromHouseId(id))).build();
    }
}

