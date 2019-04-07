package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
}
