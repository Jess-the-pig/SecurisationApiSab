package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.*;
import be.ifapme.sab.model.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {

    private Person person;

    private Cart cart;

    private Payment payment;

    private BigDecimal totalAmount;

    private OrderStatus status;


    public OrderResponse(Person person, Cart cart, Payment payment, BigDecimal totalAmount,OrderStatus status){
        this.person=person;
        this.cart=cart;
        this.payment=payment;
        this.totalAmount=totalAmount;
        this.status=status;
    }




    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }



}
