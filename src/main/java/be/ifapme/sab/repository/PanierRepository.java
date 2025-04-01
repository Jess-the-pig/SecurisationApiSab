package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier,Long> {
}
