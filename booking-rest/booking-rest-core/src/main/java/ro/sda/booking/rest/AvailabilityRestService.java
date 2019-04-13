package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.service.AvailabilityService;
import ro.sda.booking.core.service.ClientService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/availability")
public class AvailabilityRestService {

    @Autowired
    private AvailabilityService availabilityService;

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Availability> getAll() {
        return availabilityService.getAllAvailabilities();
    }

    @Path("/get/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Availability getAvailabilityById(@PathParam("id") Long id) {
        return availabilityService.getAvailabilityById(id);
    }

    @Path("/delete")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAvailability(@QueryParam("availabilityID") Long id) {
        Availability availability = availabilityService.getAvailabilityById(id);
        availabilityService.deleteAvailability(availability);
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Availability createAvailability(Availability availability){
        return availabilityService.createAvailability(availability);
    }

    @Path("/update")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAvailability(Availability availability){
        availabilityService.updateAvailability(availability);
    }
}
