package be.ifapme.sab.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carts", schema = "public")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    @ManyToOne
    private Person user;

    private Boolean cart_payed;





    public Cart(Long id, Person user){
        this.id = id;
        this.user = user;
        this.cart_payed = cart_payed;
    }
    public Cart(){}

    //Setters


    public void setCart_payed(Boolean cart_payed) {
        this.cart_payed = cart_payed;
    }

    public Boolean getCart_payed() {
        return cart_payed;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setUser(Person user) {
        this.user = user;
    }

    //Getters

    public Long getId() {
        return id;
    }

    public Person getUser() {
        return user;
    }


}
