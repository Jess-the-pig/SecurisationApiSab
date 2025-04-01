package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Admin; // Import de l'entité Admin
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
