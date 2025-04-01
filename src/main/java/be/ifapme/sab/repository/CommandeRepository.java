package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
}
