package be.ifapme.sab.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    public CartItem(Long id, Cart cart, Book book, Integer quantity, BigDecimal price){
        this.id=id;
        this.cart= cart;
        this.book = book;
        this.quantity=quantity;
        this.price = price;
    }

    public CartItem(){}


    public void setId(Long id){
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    // Calcul du montant total pour cet article
    public BigDecimal getTotalAmount() {
        return price.multiply(new BigDecimal(quantity));
    }
}
