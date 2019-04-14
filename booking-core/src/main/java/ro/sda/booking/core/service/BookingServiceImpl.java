package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.commons.EmailUtil;
import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.enums.RoomType;
import ro.sda.booking.core.repository.AvailabilityRepository;
import ro.sda.booking.core.repository.BookingRepository;

import java.util.List;

@Service("bookingService")
@Transactional(rollbackFor = Exception.class)
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    @Transactional
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);

    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Booking findByRoomType(RoomType roomType){
       return bookingRepository.findByRoomType(roomType);
    }

    @Override
    public void sendBookingMail(Booking booking) {
        String message = "Hello " + booking.getClient().getName() +
                "\n Your reservation information are:"
                + "\n room type: " + booking.getRoomType()
                + "\n number of rooms : "+ booking.getNrRooms()
                + "\n check-in date: " + booking.getCheckIn()
                + "\n" + " check-out date: " + booking.getCheckOut();
        String to = booking.getClient().getEmail();
        String subject = "Reservation for " + booking.getClient().getName();
        emailUtil.sendMail(to, subject,message);
    }
}
