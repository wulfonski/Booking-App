package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.entity.Host;

import java.util.Date;
import java.util.List;

public interface AvailabilityRepository extends EntityRepository<Availability> {
    List<Availability> findAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual(Date fromDate, Date toDate);


}
