package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.repository.HostRepository;

import java.util.List;

@Service("hostService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class HostServiceImpl implements HostService {

    @Autowired
    private HostRepository hostRepository;

    @Override
    @Transactional
    public Host createHost(Host host) {

        return hostRepository.save(host);
    }

    @Override
    public List<Host> getAllHosts() {

        return hostRepository.findAll();
    }

    @Override
    public Host getHostById(Long id) {

        return hostRepository.findById(id);
    }

    @Override
    public void deleteHost(Host host) {

        hostRepository.delete(host);
    }

    @Override
    public void updateHost(Host host) {

        hostRepository.save(host);
    }

    @Override
    public Host findByName(String name) {

        return hostRepository.findByName(name);
    }
}
