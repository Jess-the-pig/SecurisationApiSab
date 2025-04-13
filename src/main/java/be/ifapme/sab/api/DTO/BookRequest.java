package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.BookCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

public class BookRequest {

        @NotBlank
        private String title;

        private Long isbn;

        private String description;

        private String photo;

        private Float price;

        private Integer quantity;

        @Enumerated(EnumType.STRING)
        private BookCategory category_id;


        //GETTERS
        public String getTitle() {
            return title;
        }

        public Long getIsbn(){return isbn;}

        public String getDescription(){return description;}

        public String getPhoto(){return photo;}

        public Float getPrice(){return price;}

        public Integer getQuantity(){return quantity;}

        public BookCategory getCategoryid(){return category_id;}


        //Les setters

        public void setTitle(String title) {
            this.title = title;
        }

        public void setIsbn(Long isbn){this.isbn = isbn;}

        public void setDescription(String description){this.description = description;}

        public void setPhoto(String photo){this.photo = photo;}

        public void setPrice(Float price){this.price = price;}

        public void setQuantity(Integer quantity){this.quantity = quantity;}

        public void setCategoryid(BookCategory category_id){this.category_id = category_id;}



}
