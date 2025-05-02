package be.ifapme.sab.api.DTO;

public class CartResponse {


    private Long user_id;

    private Boolean cart_payed;



    public CartResponse (Long user_id, Boolean cart_payed){
        this.user_id = user_id;
        this.cart_payed=cart_payed;
    }

    //Setters

    public Boolean getCart_payed() {
        return cart_payed;
    }

    public void setCart_payed(Boolean cart_payed) {
        this.cart_payed = cart_payed;
    }


    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    //Getters


    public Long getUser_id() {
        return user_id;
    }
}
