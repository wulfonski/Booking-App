package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.service.HostService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/host")
public class HostRestService {

    @Autowired
    private HostService hostService;

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Host> getAllHosts() {
        return hostService.getAllHosts();
    }

    @Path("/get/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Host getHostById(@PathParam("id") Long id) {
        return hostService.getHostById(id);
    }


    @Path("/get/name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Host findByName(@PathParam("name") String name) {
        return hostService.findByName(name);
    }

    @Path("/delete")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteHost(@QueryParam("hostID") Long id) {
        Host host = hostService.getHostById(id);
        hostService.deleteHost(host);
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Host createHost(Host host){
        return hostService.createHost(host);
    }

    @Path("/update")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateHost(Host host){
        hostService.updateHost(host);
    }

}
