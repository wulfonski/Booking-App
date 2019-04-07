import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.service.ClientService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional

public class ClientImplTest {
    @Autowired
    private ClientService clientService;

    @Test
    @Rollback(false)
    public void testCreateClient() {
        Client client = new Client();
        client.setName("Andreea SRL");
        client.setEmail("josef@yahoo.com");
        client.setContact("0746139825");
        clientService.createClient(client);
        Assert.assertEquals("Andreea SRL", client.getName());
        Assert.assertEquals("josef@yahoo.com", client.getEmail());
        Assert.assertEquals("0746139825", client.getContact());

    }

    @Test
    @Rollback(false)
    @Transactional
    public void testGetAllClients() {
        Client client = new Client();
        client.setName("Madalina Georgiana");
        client.setEmail("van@yahoo.com");
        client.setContact("078853216");
        clientService.createClient(client);
        Client client1 = new Client();
        client1.setName("Mardare Cristina");
        client1.setEmail("mar@yahoo.com");
        client1.setContact("074453216");
        clientService.createClient(client1);
        List<Client> clients = clientService.getAllClients();
        Assert.assertEquals(3, clients.size());
    }

    @Test
    public void testGetClientById() {
        Client client = clientService.getClientById(4L);
        Long clientId = client.getId();
        String clientName = client.getName();
        Assert.assertEquals("Madalina Georgiana", clientName);
        Assert.assertEquals(new Long(4), clientId);
    }

    @Test
    @Rollback(false)
    public void testDeleteClient() {
        List<Client> allClients = clientService.getAllClients();
        int size1 = allClients.size();
        Client client = clientService.getClientById(1L);
        clientService.deleteClient(client);
        List<Client> allClients2 = clientService.getAllClients();
        int size2 = allClients2.size();
        Assert.assertEquals(size1 - 1, size2);
    }

    @Test
    @Rollback(false)
    public void testUpdateClient() {
        Client client = clientService.getClientById(4L);
        client.setName("Madalina Marin");
        String clientName = client.getName();
        clientService.updateClient(client);
        Assert.assertEquals("Madalina Marin", clientName);
    }

    @Test
    public void testFindByName() {
        Client client = clientService.findByName("Mardare Cristina");
        String clientName = client.getName();
        Long clientId = client.getId();
        Assert.assertEquals(new Long(5), clientId);
        Assert.assertEquals(new String("Mardare Cristina"), clientName);
    }


}
