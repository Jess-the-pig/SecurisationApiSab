package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.BookRequest;
import be.ifapme.sab.model.entities.Book;
import be.ifapme.sab.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    //Enregistrer un livre.
    public Book store(BookRequest bookrequest){
        Book book = new Book();
        book.setTitle(bookrequest.getTitle());
        book.setIsbn(bookrequest.getIsbn());

        Book Booksaved =  bookRepository.save(book);
        return Booksaved;
    }
}
