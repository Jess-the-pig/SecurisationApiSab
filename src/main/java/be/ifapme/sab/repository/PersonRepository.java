package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Boolean existsByUsername(String username);
    Optional<Person> findByUsername(String username);

}
