package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Property;

public interface PropertyRepository extends EntityRepository<Property> {

    public Property findByName(String name);
}
