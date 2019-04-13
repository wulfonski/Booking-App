package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.service.ClientService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/client")
public class ClientRestService {

    @Autowired
    private ClientService clientService;

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> getAll() {
        return clientService.getAllClients();
    }

    @Path("/getById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Client getClientById(@PathParam("id") Long id) {
        return clientService.getClientById(id);
    }

    @Path("/getByName/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Client findByName(@PathParam("name") String name) {
        return clientService.getByName(name);
    }

    @Path("/delete")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteClient(@QueryParam("clientID") Long id) {
        Client client = clientService.getClientById(id);
        clientService.deleteClient(client);
    }

    @Path("/createClient")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Client createClient(Client client){
        return clientService.createClient(client);
    }

    @Path("/updateClient")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateClient(Client client){
//        Client client = clientService.getClientById(id);
        clientService.updateClient(client);
    }
}
