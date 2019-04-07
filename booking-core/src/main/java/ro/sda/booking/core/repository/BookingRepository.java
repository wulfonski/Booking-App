package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.enums.RoomType;

public interface BookingRepository extends EntityRepository<Booking> {

    public Booking findByRoomType(RoomType roomType);
}
