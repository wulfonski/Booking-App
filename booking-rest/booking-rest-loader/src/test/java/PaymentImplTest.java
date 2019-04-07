import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.entity.Payment;
import ro.sda.booking.core.enums.RoomType;
import ro.sda.booking.core.service.BookingService;
import ro.sda.booking.core.service.ClientService;
import ro.sda.booking.core.service.PaymentService;
import ro.sda.booking.core.service.PropertyService;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class PaymentImplTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BookingService bookingService;

    @Test
    @Rollback(false)
    @Transactional
    public void testCreatePayment() {
        Payment payment = new Payment();
        payment.setAmount(100.50);
        payment.setBooking(bookingService.getBookingById(1l));

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 05, 13);
        Date paymentDate = cal.getTime();
        payment.setPaymentDate(paymentDate);

        paymentService.createPayment(payment);
        Assert.assertNotNull(payment);
    }

    @Test
    @Rollback(false)
    @org.springframework.transaction.annotation.Transactional
    public void getAllPaymentsTest() {
        List<Payment> payments = paymentService.getAllPayments();
        Assert.assertEquals(1, payments.size());
    }

    @Test
    @Rollback(false)
    @org.springframework.transaction.annotation.Transactional
    public void testGetPaymentById() {
        Payment payment = paymentService.getPaymentById(1L);
        Double amount = payment.getAmount();
        Assert.assertEquals(Double.valueOf(100.50), amount);
    }

    @Test
    @Rollback(false)
    public void testUpdatePayment() {
        Payment payment = paymentService.getPaymentById(1L);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 05, 20);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date paymentDate = cal.getTime();
        payment.setPaymentDate(paymentDate);
        paymentService.updatePayment(payment);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(2019, 05, 20);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        Date expected = cal1.getTime();
        Assert.assertEquals(expected,payment.getPaymentDate());
    }

    @Test
    @Rollback(false)
    @org.springframework.transaction.annotation.Transactional
    public void testFindByAmount() {
        Payment payment = paymentService.findByAmount(100.50);
        Long bookingID = payment.getId();
        Assert.assertEquals(Long.valueOf(1), bookingID);
    }

    @Test
    @Rollback(false)
    public void testDeletePayment() {
        List<Payment> allPayments = paymentService.getAllPayments();
        int size1 = allPayments.size();
        Payment payment = paymentService.getPaymentById(1L);
        paymentService.deletePayment(payment);
        List<Payment> allPayments2 = paymentService.getAllPayments();
        int size2 = allPayments2.size();
        Assert.assertEquals(size1 - 1, size2);
    }
}
