package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.enums.RoomType;
import ro.sda.booking.core.service.BookingService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/booking")
public class BookingRestService {

    @Autowired
    private BookingService bookingService;

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @Path("/get/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Booking getBookingById(@PathParam("id") Long id) {
        return bookingService.getBookingById(id);
    }

    @Path("/get/room-type/{roomType}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Booking findByRoomType(@PathParam("roomType") RoomType roomType) {
        return bookingService.findByRoomType(roomType);
    }

    @Path("/delete")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteBooking(@QueryParam("bookingID") Long id) {
        Booking booking = bookingService.getBookingById(id);
        bookingService.deleteBooking(booking);
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Booking createBooking(Booking booking){
        return bookingService.createBooking(booking);
    }

    @Path("/update")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBooking(Booking booking){
        bookingService.updateBooking(booking);
    }
}
