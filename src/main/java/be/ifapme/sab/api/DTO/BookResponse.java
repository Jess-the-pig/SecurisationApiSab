package be.ifapme.sab.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookResponse {
    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    @Size(max = 13)
    private String isbn;

    @NotBlank
    @Size(max = 150)
    private String description;

    @NotBlank
    @Size(max = 30)
    private String photo;

    @NotBlank
    @Size(max = 5)
    private Float price;

    @NotBlank
    @Size(max = 3)
    private Integer quantity;

    @NotBlank
    @Size(max = 1)
    private Integer category_id;


    public BookResponse(String title, String isbn, String description, String photo, Float price, Integer quantity, Integer category_id){
        this.title = title;
        this.isbn=isbn;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.quantity = quantity;
        this.category_id = category_id;

    }

    public BookResponse(String title, String description){
        this.title = title;
        this.description = description;
    }




    //GETTERS
    public String getTitle() {
        return title;
    }

    public String getIsbn(){return isbn;}

    public String getDescription(){return description;}

    public String getPhoto(){return photo;}

    public Float getPrice(){return price;}

    public Integer getQuantity(){return quantity;}

    public Integer getCategoryid(){return category_id;}

    //SETTERS

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn){this.isbn = isbn;}

    public void setDescription(String description){this.description = description;}

    public void setPhoto(String photo){this.photo = photo;}

    public void setPrice(Float price){this.price = price;}

    public void setQuantity(Integer quantity){this.quantity = quantity;}

    public void setCategoryid(Integer category_id){this.category_id = category_id;}

}
