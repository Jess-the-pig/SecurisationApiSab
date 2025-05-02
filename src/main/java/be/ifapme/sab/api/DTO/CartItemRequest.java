package be.ifapme.sab.api.DTO;

public class CartItemRequest {


    private Long item_id;

    private Long cart_id;

    private Integer item_quantity;

    public CartItemRequest(Long item_id, Long cart_id, Integer item_quantity){
        this.item_id=item_id;
        this.cart_id=cart_id;
        this.item_quantity=item_quantity;
    }

    public void setItem_quantity(Integer item_quantity) {
        this.item_quantity = item_quantity;
    }

    public Integer getItem_quantity() {
        return item_quantity;
    }


    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }


    public Long getCart_id() {
        return cart_id;
    }

    public Long getItem_id() {
        return item_id;
    }
}
