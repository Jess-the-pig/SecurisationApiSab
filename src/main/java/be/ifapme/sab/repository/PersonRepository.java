package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Set<Person> findByIdIn(Collection<Long> ids);

    Optional<Person> findByUsername(String username);

    Optional<Person> findByEmail(String email);
}
