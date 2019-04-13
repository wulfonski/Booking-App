import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.enums.RoomType;
import ro.sda.booking.core.service.BookingService;
import ro.sda.booking.core.service.ClientService;
import ro.sda.booking.core.service.PropertyService;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class BookingImplTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ClientService clientService;

    @Test
    @Rollback(false)
    @Transactional
    public void testCreateBooking() {
        Booking booking = new Booking();
        booking.setProperty(propertyService.getPropertyById(1L));
        booking.setClient(clientService.getClientById(2L));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 05, 13);
        Date checkInDate = cal.getTime();
        booking.setCheckIn(checkInDate);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(0);
        cal1.set(2019, 05, 20);
        Date checkOutDate = cal1.getTime();
        booking.setCheckOut(checkOutDate);

        booking.setNbOfPersons(2);
        booking.setRoomType(RoomType.DOUBLE);
        booking.setNrRooms(1);

        Date date = new Date();
        booking.setBookingDate(date);

        int nbOfPerson = booking.getNbOfPersons();
        RoomType roomType = booking.getRoomType();

        bookingService.createBooking(booking);
        Assert.assertEquals(2, nbOfPerson);
        Assert.assertEquals(RoomType.DOUBLE, roomType);
    }

    @Test
    @Rollback(false)
    @org.springframework.transaction.annotation.Transactional
    public void getAllBookingsTest() {
        List<Booking> bookings = bookingService.getAllBookings();
        Assert.assertEquals(1, bookings.size());
    }

    @Test
    @Rollback(false)
    @org.springframework.transaction.annotation.Transactional
    public void testGetBookingById() {
        Booking booking = bookingService.getBookingById(2L);
        int nbOfPerson = booking.getNbOfPersons();
        RoomType roomType = booking.getRoomType();
        Assert.assertEquals(2, nbOfPerson);
        Assert.assertEquals(RoomType.SINGLE, roomType);
    }

    @Test
    @Rollback(false)
    public void testUpdateBooking() {
        Booking booking = bookingService.getBookingById(4L);
        booking.setRoomType(RoomType.DOUBLE);
        bookingService.updateBooking(booking);
        Assert.assertEquals(RoomType.DOUBLE, booking.getRoomType());
    }

    @Test
    @Rollback(false)
    @org.springframework.transaction.annotation.Transactional
    public void testFindByRoomType() {
        Booking booking = bookingService.findByRoomType(RoomType.DOUBLE);
        int nbOfPerson = booking.getNbOfPersons();
        Date checkIn = booking.getCheckIn();
        Calendar cal = Calendar.getInstance();
        cal.set(2019, 03, 10);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date expected = cal.getTime();

        Assert.assertEquals(2, nbOfPerson);
        Assert.assertEquals(checkIn, expected);
    }

    @Test
    @Rollback(false)
    public void testDeleteBooking() {
        List<Booking> allBookings = bookingService.getAllBookings();
        int size1 = allBookings.size();
        Booking booking = bookingService.getBookingById(1L);
        bookingService.deleteBooking(booking);
        List<Booking> allBookings2 = bookingService.getAllBookings();
        int size2 = allBookings2.size();
        Assert.assertEquals(size1 - 1, size2);
    }
}
