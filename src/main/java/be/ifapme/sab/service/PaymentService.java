package be.ifapme.sab.service;

import be.ifapme.sab.model.entities.Cart;
import be.ifapme.sab.model.entities.Payment;
import be.ifapme.sab.model.entities.enums.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public Payment processPayment(Cart cart){
        logger.info("Payememnt en cours");
        Payment payment = new Payment();
        payment.setAmount(cart.calculateTotalAmount());
        payment.setStatus(PaymentStatus.SUCCESS);
        return payment;
    }
}
