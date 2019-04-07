package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;
import ro.sda.booking.core.enums.RoomType;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bookings", schema = "booking")
public class Booking extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Temporal(TemporalType.DATE)
    @Column(name = "check_in", nullable = false)
    private Date checkIn;

    @Temporal(TemporalType.DATE)
    @Column(name = "check_out", nullable = false)
    private Date checkOut;

    @Column(name = "nr_of_persons", nullable = false)
    private int nbOfPersons;

    @Column(name = "room_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "nr_of_rooms", nullable = false)
    private int nrRooms;

    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getNbOfPersons() {
        return nbOfPersons;
    }

    public void setNbOfPersons(int nbOfPersons) {
        this.nbOfPersons = nbOfPersons;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getNrRooms() {
        return nrRooms;
    }

    public void setNrRooms(int nrRooms) {
        this.nrRooms = nrRooms;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return getNbOfPersons() == booking.getNbOfPersons() &&
                getNrRooms() == booking.getNrRooms() &&
                Objects.equals(getClient(), booking.getClient()) &&
                Objects.equals(getProperty(), booking.getProperty()) &&
                Objects.equals(getCheckIn(), booking.getCheckIn()) &&
                Objects.equals(getCheckOut(), booking.getCheckOut()) &&
                getRoomType() == booking.getRoomType() &&
                Objects.equals(getBookingDate(), booking.getBookingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClient(), getProperty(), getCheckIn(), getCheckOut(), getNbOfPersons(), getRoomType(), getNrRooms(), getBookingDate());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "client=" + client +
                ", property=" + property +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", nbOfPersons=" + nbOfPersons +
                ", roomsType=" + roomType +
                ", nrRooms=" + nrRooms +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
