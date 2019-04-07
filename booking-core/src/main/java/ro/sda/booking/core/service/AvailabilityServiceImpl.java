package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.repository.AvailabilityRepository;

import java.util.List;

@Service("availabilityService")
@Transactional(rollbackFor = Exception.class)
public class AvailabilityServiceImpl implements AvailabilityService{

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    @Transactional
    public Availability createAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public List<Availability> getAllAvailability() {
           return availabilityRepository.findAll();
    }

    @Override
    public Availability getAvailabilityById(Long id) {
        return availabilityRepository.findById(id);
    }

    @Override
    public void deleteAvailability(Availability availability) {
        availabilityRepository.delete(availability);
    }

    @Override
    public void updateAvailability(Availability availability) {
        availabilityRepository.save(availability);
    }

}
