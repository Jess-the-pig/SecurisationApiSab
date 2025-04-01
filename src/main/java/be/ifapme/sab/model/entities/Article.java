package be.ifapme.sab.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "isbn", nullable = false, unique = true) // ISBN unique
    private String isbn;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT") // Pour stocker une longue description
    private String description;

    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "prix", nullable = false, precision = 10, scale = 2) // Utilisation de BigDecimal
    private BigDecimal prix;

    @Column(name = "quantite", nullable = false)
    private Integer quantite;


}
