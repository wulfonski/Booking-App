package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Rating;
import ro.sda.booking.core.service.RatingService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/rating")
public class RatingRestService {

    @Autowired
    private RatingService ratingService;

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @Path("/get/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Rating getRatingById(@PathParam("id") Long id) {
        return ratingService.getRatingById(id);
    }

    @Path("/delete")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteRating(@QueryParam("ratingID") Long id) {
        Rating rating = ratingService.getRatingById(id);
        ratingService.deleteRating(rating);
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Rating createRating(Rating rating) {
        return ratingService.createRating(rating);
    }

    @Path("/update")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRating(Rating rating) {
        ratingService.updateRating(rating);
    }

    @Path("/get/rating/{rating}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Rating findByRating(@PathParam("rating") int rating) {
        return ratingService.findByRating(rating);
    }
}
