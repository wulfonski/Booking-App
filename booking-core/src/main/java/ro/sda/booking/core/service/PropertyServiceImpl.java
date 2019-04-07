package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.repository.ClientRepository;
import ro.sda.booking.core.repository.PropertyRepository;

import java.util.List;

@Service("propertyService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    @Transactional
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public void deleteProperty(Property property) {
        propertyRepository.delete(property);
    }

    @Override
    public void updateProperty(Property property) {
        propertyRepository.save(property);
    }

    @Override
    public Property findByName(String name) {

        return propertyRepository.findByName(name);
    }

}
