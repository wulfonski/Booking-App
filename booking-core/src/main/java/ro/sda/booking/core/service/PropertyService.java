package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.entity.Property;

import java.util.List;

public interface PropertyService {
    Property createProperty(Property property);

    List<Property> getAllProperties();

    Property getPropertyById(Long id);

    void deleteProperty(Property property);

    void updateProperty(Property property);

    public Property findByName(String name);
}
