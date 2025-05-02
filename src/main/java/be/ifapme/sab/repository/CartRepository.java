package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Cart;
import be.ifapme.sab.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(Person person);
}
