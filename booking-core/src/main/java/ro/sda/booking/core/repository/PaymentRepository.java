package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.entity.Payment;

public interface PaymentRepository extends EntityRepository<Payment> {

    public Payment findByAmount(Double amount);
}
