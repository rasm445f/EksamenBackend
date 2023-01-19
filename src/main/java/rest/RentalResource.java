package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RentalDto;
import entities.Rental;
import errorhandling.API_Exception;
import facades.RentalFacade;
import javassist.NotFoundException;
import utils.EMF_Creator;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("rental")
public class RentalResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final RentalFacade FACADE =  RentalFacade.getRentalFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() throws NotFoundException {
        return Response.ok().entity(GSON.toJson(FACADE.getAllRentals())).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRental(@PathParam("id") int id) throws API_Exception {
        return Response.ok().entity(GSON.toJson(FACADE.getRentalById(id))).build();
    }

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String rental) {
        Rental rentalTwo =GSON.fromJson(rental, Rental.class);
        Rental burner = new Rental(rentalTwo.getStartDate(), rentalTwo.getEndDate(),rentalTwo.getPriceAnnual(),rentalTwo.getDeposit(),rentalTwo.getJTHouseID());
        Rental newRental = FACADE.createRental(burner);
        return Response.ok().entity(GSON.toJson(newRental)).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") int id) throws EntityNotFoundException, API_Exception {
        RentalDto deleted = FACADE.deleteRental(id);
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }

    @GET
    @Path("/tenant/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBoatByHarborID(@PathParam("id") int id) throws NotFoundException {
        return Response.ok().entity(GSON.toJson(FACADE.getAllRentalsOfTenantID(id))).build();
    }
}

