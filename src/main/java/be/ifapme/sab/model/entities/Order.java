package be.ifapme.sab.model.entities;

import be.ifapme.sab.model.entities.enums.OrderStatus;
import jakarta.persistence.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(nullable = false)
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @Column(nullable = true)
    private LocalDateTime shippingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public void setItems(List<OrderItem> items) {
        this.items = items;

        // Gérer la relation bidirectionnelle
        if (items != null) {
            for (OrderItem item : items) {
                item.setOrder(this);
            }
        }
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
