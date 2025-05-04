package be.ifapme.sab.model.entities;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import be.ifapme.sab.model.entities.enums.PaymentStatus;
import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;  // On associe le paiement à un panier

    @Column(nullable = false)
    private BigDecimal amount;  // Montant payé

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;  // Statut du paiement

    @Column(nullable = false)
    private LocalDateTime paymentDate;  // Date du paiement

    @Column
    private String transactionId;  // Identifiant de la transaction (fournisseur de paiement)

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
