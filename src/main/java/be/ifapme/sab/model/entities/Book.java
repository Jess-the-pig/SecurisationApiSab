package be.ifapme.sab.model.entities;

import jakarta.persistence.*;

@Entity
public class Book {

    //Variables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long isbn;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookCategory category_id;

    //Init

    public Book(){}

    public Book(Long id, String title, Long isbn, String description, String photo, Float price, Integer quantity, BookCategory category_id){
        this.id=id;
        this.title=title;
        this.isbn=isbn;
        this.description=description;
        this.photo=photo;
        this.price=price;
        this.quantity=quantity;
        this.category_id=category_id;
    }

    //Getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setQuantity(Integer quantity){this.quantity= quantity;}

    public void setCategoryid(BookCategory category_id){this.category_id = category_id;}
}
