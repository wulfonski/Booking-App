package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "payments", schema = "booking")
public class Payment extends BaseEntity {

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(getAmount(), payment.getAmount()) &&
                Objects.equals(getPaymentDate(), payment.getPaymentDate()) &&
                Objects.equals(getBooking(), payment.getBooking());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount(), getPaymentDate(), getBooking());
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", booking=" + booking +
                '}';
    }
}
