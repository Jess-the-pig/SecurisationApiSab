package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client connect();

}
