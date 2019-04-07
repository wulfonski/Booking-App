import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.service.HostService;
import ro.sda.booking.core.service.PropertyService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional

public class PropertyImplTest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private HostService hostService;

    @Test
    @Rollback(false)
    public void testCreateProperty() {
        Property property = new Property();
        property.setName("Mariko Inn");
        property.setHost(hostService.getHostById(3L));
        property.setEmail("contact@marikoinn.ro");
        property.setContact("074235618");
        property.setCity("Roman");
        propertyService.createProperty(property);
        Assert.assertEquals("Mariko Inn", property.getName());
        Assert.assertEquals("contact@marikoinn.ro", property.getEmail());
        Assert.assertEquals("074235618", property.getContact());
        Assert.assertEquals("Roman", property.getCity());

    }

    @Test
    @Rollback(false)
    @Transactional
    public void testGetAllProperties() {
        Property property = new Property();
        property.setName("Basarabia");
        property.setHost(hostService.getHostById(2L));
        property.setEmail("contact@basarabia.ro");
        property.setContact("074125489");
        property.setCity("Dancu");
        propertyService.createProperty(property);
        Property property1 = new Property();
        property1.setName("Decebal");
        property1.setHost(hostService.getHostById(3L));
        property1.setEmail("contact@decebal.ro");
        property1.setContact("074169853");
        property1.setCity("Bacau");
        propertyService.createProperty(property1);
        List<Property> properties = propertyService.getAllProperties();
        Assert.assertEquals(3, properties.size());
    }

    @Test
    public void testGetPropertyById() {
        Property property = propertyService.getPropertyById(4L);
        Long propertyId = property.getId();
        String propertyName = property.getName();
        Assert.assertEquals("Decebal", propertyName);
        Assert.assertEquals(new Long(4), propertyId);
    }

    @Test
    @Rollback(false)
    public void testDeleteProperty() {
        List<Property> allProperties = propertyService.getAllProperties();
        int size1 = allProperties.size();
        Property property = propertyService.getPropertyById(3L);
        propertyService.deleteProperty(property);
        List<Property> allProperties2 = propertyService.getAllProperties();
        int size2 = allProperties2.size();
        Assert.assertEquals(size1 - 1, size2);
    }

    @Test
    @Rollback(false)
    public void testUpdateProperty() {
        Property property = propertyService.getPropertyById(4L);
        property.setName("Moldova");
        String propertyName = property.getName();
        propertyService.updateProperty(property);
        Assert.assertEquals("Moldova", propertyName);
    }

    @Test
    public void testFindByName() {
        Property property = propertyService.findByName("Mariko Inn");
        String propertyName = property.getName();
        Long propertyId = property.getId();
        Assert.assertEquals(new Long(1), propertyId);
        Assert.assertEquals(new String("Mariko Inn"), propertyName);
    }
}
