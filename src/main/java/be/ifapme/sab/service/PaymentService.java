package be.ifapme.sab.service;

import be.ifapme.sab.model.entities.Cart;
import be.ifapme.sab.model.entities.Payment;
import be.ifapme.sab.model.entities.enums.PaymentStatus;

public class PaymentService {

    public Payment processPayment(Cart cart){
        Payment payment = new Payment();
        payment.setAmount(cart.calculateTotalAmount());

        payment.setStatus(PaymentStatus.SUCCESS);
        return payment;
    }
}
