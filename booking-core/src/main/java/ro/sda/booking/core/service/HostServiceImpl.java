package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.repository.HostRepository;

@Service("hostService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class HostServiceImpl implements HostService {

    @Autowired
    private HostRepository hostRepository;
}
