package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.enums.CartStatus;

public class CartResponse {


    private Long user_id;

    private CartStatus status;



    public CartResponse (Long user_id, CartStatus status){
        this.user_id = user_id;
        this.status=status;
    }

    //Setters


    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    //Getters


    public Long getUser_id() {
        return user_id;
    }
}
