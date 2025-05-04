package be.ifapme.sab.model.entities;

import be.ifapme.sab.model.entities.enums.CartStatus;
import be.ifapme.sab.model.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "carts", schema = "public")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    @ManyToOne
    private Person user;

    private CartStatus status;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;



    public Cart(Long id, Person user){
        this.id = id;
        this.user = user;
        this.status = status;
    }
    public Cart(){}

    //Setters


    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public CartStatus getStatus() {
        return status;
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


    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    // Calcul du montant total du panier
    public BigDecimal calculateTotalAmount() {
        return items.stream()
                .map(CartItem::getTotalAmount)  // On récupère le montant total de chaque article
                .reduce(BigDecimal.ZERO, BigDecimal::add);  // On additionne les montants de tous les articles
    }


}
