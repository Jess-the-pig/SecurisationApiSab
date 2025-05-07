package be.ifapme.sab.service;

import be.ifapme.sab.model.entities.Cart;
import be.ifapme.sab.model.entities.Payment;
import be.ifapme.sab.model.entities.enums.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    public Payment processPayment(Cart cart){
        log.debug("Payememnt en cours");
        Payment payment = new Payment();
        payment.setAmount(cart.calculateTotalAmount());
        payment.setStatus(PaymentStatus.SUCCESS);
        return payment;
    }
}
