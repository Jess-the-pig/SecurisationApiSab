package be.ifapme.sab.api.DTO;

public class CartItemResponse {

    private Long item_id;


    private Integer item_quantity;

    public CartItemResponse(Long item_id,Integer item_quantity){
        this.item_id=item_id;
        this.item_quantity=item_quantity;
    }

    public void setItem_quantity(Integer item_quantity) {
        this.item_quantity = item_quantity;
    }

    public Integer getItem_quantity() {
        return item_quantity;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }


    public Long getItem_id() {
        return item_id;
    }
}
