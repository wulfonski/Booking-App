import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.enums.RoomType;
import ro.sda.booking.core.service.AvailabilityService;
import ro.sda.booking.core.service.PropertyService;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class AvailabilityImplTest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private AvailabilityService availabilityService;

    @Test
    @Rollback(false)
    @Transactional
    public void testCreateAvailability() {
        Availability availability = new Availability();
        availability.setProperty(propertyService.getPropertyById(1L));
        availability.setRoomName("3C");

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, Calendar.APRIL, 01);
        Date fromDate = cal.getTime();
        availability.setFromDate(fromDate);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(0);
        cal1.set(2019, Calendar.DECEMBER, 31);
        Date toDate = cal1.getTime();
        availability.setToDate(toDate);

        availability.setRoomType(RoomType.DOUBLE);
        availability.setPriceSingle(100.99);
        availability.setPriceDouble(155.00);
        availabilityService.createAvailability(availability);
        Assert.assertNotNull(availability);
    }

    @Test
    @Rollback(false)
    @org.springframework.transaction.annotation.Transactional
    public void testGetAllAvailabilities() {
        List<Availability> allAvailabilities = availabilityService.getAllAvailabilities();
        Assert.assertEquals(1, allAvailabilities.size());
    }

    @Test
    @Rollback(false)
    @org.springframework.transaction.annotation.Transactional
    public void testGetAvailabilityById() {
        Availability availability = availabilityService.getAvailabilityById(2L);
        String roomName = availability.getRoomName();
        RoomType roomType = availability.getRoomType();
        Assert.assertEquals("3C", roomName);
        Assert.assertEquals(RoomType.DOUBLE, roomType);
    }

    @Test
    @Rollback(false)
    public void testUpdateAvailability() {
        Availability availability = availabilityService.getAvailabilityById(2L);
        availability.setRoomType(RoomType.SINGLE);
        availabilityService.updateAvailability(availability);
        Assert.assertEquals(RoomType.SINGLE, availability.getRoomType());
    }

    @Test
    @Rollback(false)
    public void testDeleteAvailability() {
        List<Availability> allAvailabilities = availabilityService.getAllAvailabilities();
        int size1 = allAvailabilities.size();
        Availability availability = availabilityService.getAvailabilityById(2L);
        availabilityService.deleteAvailability(availability);
        List<Availability> allAvailabilities2 = availabilityService.getAllAvailabilities();
        int size2 = allAvailabilities2.size();
        Assert.assertEquals(size1 - 1, size2);
    }

    @Test
    @Rollback(false)
    public void testFindAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, Calendar.JANUARY, 15);
        Date fromDate = cal.getTime();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(0);
        cal1.set(2019, Calendar.MAY, 8);
        Date toDate = cal1.getTime();

        List<Availability> allAvailabilities = availabilityService.findAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual(fromDate, toDate);
        Assert.assertEquals(2, allAvailabilities.size());
    }

}
