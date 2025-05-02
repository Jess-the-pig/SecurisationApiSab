package be.ifapme.sab.api.DTO;

import jakarta.validation.constraints.NotBlank;

public class BookRequest {

        @NotBlank
        private String title;

        private String isbn;

        private String description;

        private String photo;

        private Float price;

        private Integer quantity;


        private Integer category_id;


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


        //Les setters

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
