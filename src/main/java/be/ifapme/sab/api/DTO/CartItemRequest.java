package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.Book;
import be.ifapme.sab.model.entities.Cart;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CartItemRequest {

    @NotBlank
    private Cart cart;

    @NotBlank
    private Book book;

    @NotBlank
    @Size(max = 3)
    private Integer quantity;

    @NotBlank
    @Size(max = 5)
    private BigDecimal price;


    public CartItemRequest(Cart cart, Book book, Integer quantity, BigDecimal price){
        this.cart=cart;
        this.book=book;
        this.quantity=quantity;
        this.price=price;
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
}
