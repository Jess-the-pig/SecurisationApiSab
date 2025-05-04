package be.ifapme.sab.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookRequest {

        @NotBlank
        @Size(max = 30, message = "Le titre ne doit pas contenir lus de 30 caractères")
        private String title;

        @NotBlank
        @Size(max = 13, message = "L'isbn ne contient pas plus de 13 caractères")
        private String isbn;

        @NotBlank
        @Size(max = 150, message = "La description doit rester courte")
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
