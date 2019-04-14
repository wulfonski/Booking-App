package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.service.PropertyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/property")
public class PropertyRestService {

    @Autowired
    private PropertyService propertyService;

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @Path("/get/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Property getPropertyById(@PathParam("id") Long id) {
        return propertyService.getPropertyById(id);
    }

    @Path("/get/name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Property findByName(@PathParam("name") String name) {
        return propertyService.findByName(name);
    }

    @Path("/delete")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProperty(@QueryParam("propertyID") Long id) {
        Property property = propertyService.getPropertyById(id);
        propertyService.deleteProperty(property);
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Property createProperty(Property property){
        return propertyService.createProperty(property);
    }

    @Path("/update")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProperty(Property property){
        propertyService.updateProperty(property);
    }
}
