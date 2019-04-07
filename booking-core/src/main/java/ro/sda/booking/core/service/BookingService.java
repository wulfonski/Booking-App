package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.enums.RoomType;

import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    void deleteBooking(Booking booking);

    void updateBooking(Booking booking);

    Booking findByRoomType(RoomType roomType);
}
