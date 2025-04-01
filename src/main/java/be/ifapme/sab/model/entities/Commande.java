package be.ifapme.sab.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "ClientUIID", nullable = false, unique = true)
    private UUID client;


    @Column(name = "delivery_date", nullable = false)
    private Timestamp DeliveryDate;

    @Column(name = "prix", nullable = false)
    private Float prix;


}
