package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Host;

import java.util.List;

public interface HostService {

    Host createHost(Host host);

    List<Host> getAllHosts();

    Host getHostById(Long id);

    void deleteHost(Host host);

    void updateHost(Host host);

    public Host findByName(String name);
}
