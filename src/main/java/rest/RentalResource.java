package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RentalDto;
import dtos.UserDTO;
import entities.Role;
import entities.Rental;
import entities.Tenant;
import entities.User;
import errorhandling.API_Exception;
import facades.RentalFacade;
import facades.UserFacade;
import javassist.NotFoundException;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        Rental burner = new Rental(rentalTwo.getStartDate(), rentalTwo.getEndDate(),rentalTwo.getPriceAnnual(),rentalTwo.getDeposit(),rentalTwo.getJTHouseID(),rentalTwo.getTenants());
        Rental newRental = FACADE.createRental(burner);
        return Response.ok().entity(GSON.toJson(newRental)).build();

    }
//    @PUT
//    @Path("/{id}")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public Response update(@PathParam("id") int id, String content) throws EntityNotFoundException {
//        RentalDto rentalJson = GSON.fromJson(content, RentalDto.class);
//        Rental rental = rentalJson.toRental();
//        RentalDto updated = FACADE.updateRental(id,rental);
//        return Response.ok().entity(GSON.toJson(updated)).build();
//    }
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




//    @POST
//    @Path("/favorite")
//    @Consumes({MediaType.APPLICATION_JSON})
//    public Response favorite(String favoriteInfo) throws API_Exception {
//        Favorites favorites = GSON.fromJson(favoriteInfo,Favorites.class);
//        FavoritesDTO theFan = FACADE.addFavorite(favorites);
//        return Response.ok().entity(GSON.toJson(theFan)).build();
//    }
//
//    @GET
//    @Path("/{id}+favorite")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response getAllFavorites(@PathParam("id")int id) throws API_Exception, IOException {
//        List<FavoritesDTO> favoritesList = FACADE.getAllFavoritesFromID(id);
//        List<CharityDTO> getThese = new ArrayList<>();
//        AllCategories allCategories = new AllCategories();
//        for (FavoritesDTO f : favoritesList) {
//            boolean accepted = false;
//            for (String s: allCategories.getList()) {
//                if(accepted == true){break;}
//                String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/" + s + "?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=50");
//                NonProfitDTO nonProfitDTO = GSON.fromJson(nonprofit, NonProfitDTO.class);
//                for (CharityDTO c:nonProfitDTO.getNonprofits()) {
//                    if(accepted == true){break;}
//                    if (c.getSlug().equals(f.getSlug())){
//                        getThese.add(c);
//                        accepted = true;
//                    }
//                }
//            }
//        }
//        String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/pets?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=50");
//        NonProfitDTO nonProfitDTO =GSON.fromJson(nonprofit, NonProfitDTO.class);
//        nonProfitDTO.setNonprofits(getThese);
//        return  Response.ok().entity(GSON.toJson(nonProfitDTO)).build();
//    }
}

