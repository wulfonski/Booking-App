package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Client;

import java.util.List;

public interface ClientService {
    Client createClient(Client client);

    List<Client> getAllClients();

    Client getClientById(Long id);

    void deleteClient(Client client);

    void updateClient(Client client);

    public Client findByName(String name);
}
