package be.ifapme.sab.api.DTO;

public class CartRequest {



    private Long user_id;

    private Boolean cart_payed;

    private Long cart_id;



    public CartRequest (Long user_id, Boolean cart_payed,Long cart_id){
        this.user_id = user_id;
        this.cart_payed=cart_payed;
        this.cart_id=cart_id;
    }

    //Setters


    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

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
