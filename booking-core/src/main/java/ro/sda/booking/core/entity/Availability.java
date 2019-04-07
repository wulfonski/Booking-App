package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;
import ro.sda.booking.core.enums.RoomType;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "availability", schema = "booking")
public class Availability extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "room_name", length = 50, nullable = false)
    private String roomName;

    @Temporal(TemporalType.DATE)
    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "to_date", nullable = false)
    private Date toDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", length = 50, nullable = false)
    private RoomType roomType;

    @Column(name = "price_double", length = 50, nullable = false)
    private Double priceDouble;

    @Column(name = "price_single", length = 50, nullable = false)
    private Double priceSingle;

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date from) {
        this.fromDate = from;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date to) {
        this.toDate = to;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Double getPriceDouble() {
        return priceDouble;
    }

    public void setPriceDouble(Double priceDouble) {
        this.priceDouble = priceDouble;
    }

    public Double getPriceSingle() {
        return priceSingle;
    }

    public void setPriceSingle(Double priceSingle) {
        this.priceSingle = priceSingle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Availability)) return false;
        Availability that = (Availability) o;
        return Objects.equals(getProperty(), that.getProperty()) &&
                Objects.equals(getRoomName(), that.getRoomName()) &&
                Objects.equals(getFromDate(), that.getFromDate()) &&
                Objects.equals(getToDate(), that.getToDate()) &&
                Objects.equals(getRoomType(), that.getRoomType()) &&
                Objects.equals(getPriceDouble(), that.getPriceDouble()) &&
                Objects.equals(getPriceSingle(), that.getPriceSingle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProperty(), getRoomName(), getFromDate(), getToDate(), getRoomType(), getPriceDouble(), getPriceSingle());
    }

    @Override
    public String toString() {
        return "Availability{" +
                "property=" + property +
                ", roomName='" + roomName + '\'' +
                ", from=" + fromDate +
                ", to=" + toDate +
                ", roomType='" + roomType + '\'' +
                ", priceDouble=" + priceDouble +
                ", priceSingle=" + priceSingle +
                '}';
    }
}
