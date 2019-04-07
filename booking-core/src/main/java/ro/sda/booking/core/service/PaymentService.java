package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment createPayment(Payment payment);

    List<Payment> getAllPayments();

    Payment getPaymentById(Long id);

    void deletePayment(Payment payment);

    void updatePayment(Payment payment);

    Payment findByAmount(Double amount);
}
