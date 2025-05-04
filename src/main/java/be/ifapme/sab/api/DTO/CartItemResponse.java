package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.Book;
import be.ifapme.sab.model.entities.Cart;

import java.math.BigDecimal;

public class CartItemResponse {


    private Cart cart;

    private Book book;

    private Integer quantity;

    private BigDecimal price;


    public CartItemResponse(Cart cart, Book book, Integer quantity, BigDecimal price){
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
