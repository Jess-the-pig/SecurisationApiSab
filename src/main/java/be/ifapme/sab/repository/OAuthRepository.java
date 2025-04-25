package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.OAuthPerson;
import be.ifapme.sab.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuthRepository extends JpaRepository<OAuthPerson,Long> {
    Optional<OAuthPerson> findByEmail(String email);
    Boolean existsByEmail(String email);
}
