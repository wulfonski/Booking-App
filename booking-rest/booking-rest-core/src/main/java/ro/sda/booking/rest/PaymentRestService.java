package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.entity.Payment;
import ro.sda.booking.core.service.PaymentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/payment")
public class PaymentRestService {

    @Autowired
    private PaymentService paymentService;

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @Path("/get/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Payment getPaymentById(@PathParam("id") Long id) {
        return paymentService.getPaymentById(id);
    }

    @Path("/delete")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public void deletePayment(@QueryParam("paymentID") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        paymentService.deletePayment(payment);
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Payment createPayment(Payment payment) {
        return paymentService.createPayment(payment);
    }

    @Path("/update")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePayment(Payment payment) {
        paymentService.updatePayment(payment);
    }

    @Path("/get/amount/{amount}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Payment findByAmount(@PathParam("amount") Double amount) {
        return paymentService.findByAmount(amount);
    }
}
