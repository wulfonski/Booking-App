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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Path("/get/between")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Availability> getAvailabilityFromDateToDate(@QueryParam("from") String fromDate,@QueryParam("to") String toDate ) throws ParseException {
        Date fromDate1 =new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
        Date toDate1 =new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
        return availabilityService.findAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual(fromDate1, toDate1);
    }
}
