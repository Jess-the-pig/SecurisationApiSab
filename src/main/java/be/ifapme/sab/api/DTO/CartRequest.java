package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.enums.CartStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CartRequest {


    @NotBlank
    private Long user_id;
    @NotBlank
    private Long cart_id;


    @NotNull
    @Enumerated(EnumType.STRING)
    private CartStatus status;


    public CartRequest (Long user_id, CartStatus status,Long cart_id){
        this.user_id = user_id;
        this.status=status;
        this.cart_id=cart_id;
    }

    //Setters


    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public CartStatus getCart_payed() {
        return status;
    }

    public void setCart_payed(Boolean cart_payed) {
        this.status = status;
    }


    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    //Getters


    public Long getUser_id() {
        return user_id;
    }

}
