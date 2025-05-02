package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Book;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
   Optional<Book> findByIsbn(String isbn);
}
