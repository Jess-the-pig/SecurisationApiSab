package be.ifapme.sab.model.entities;

import jakarta.persistence.*;

@Entity
public class Book {
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
    private Long quantity;

    @Column(nullable = false)
    private Long category_id;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Long getIsbn(){return isbn}

    



    public void setTitle(String title) {
        this.title = title;
    }
}
