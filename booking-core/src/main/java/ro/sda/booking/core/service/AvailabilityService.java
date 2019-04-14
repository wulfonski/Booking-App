package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Availability;

import java.util.Date;
import java.util.List;

public interface AvailabilityService {

    Availability createAvailability(Availability availability);

    List<Availability> getAllAvailabilities();

    Availability getAvailabilityById(Long id);

    void deleteAvailability(Availability availability);

    void updateAvailability(Availability availability);

    List<Availability> findAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual(Date fromDate, Date toDate);
}
