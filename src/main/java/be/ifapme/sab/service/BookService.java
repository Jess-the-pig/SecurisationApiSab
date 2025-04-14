package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.BookRequest;
import be.ifapme.sab.api.DTO.BookResponse;
import be.ifapme.sab.model.entities.Book;
import be.ifapme.sab.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    //Enregistrer un livre.
    public BookResponse store(BookRequest bookrequest){
        Book book = new Book();
        book.setTitle(bookrequest.getTitle());
        book.setIsbn(bookrequest.getIsbn());
        book.setDescription(bookrequest.getDescription());
        book.setPhoto(bookrequest.getPhoto());
        book.setPrice(bookrequest.getPrice());
        book.setQuantity(bookrequest.getQuantity());
        book.setCategoryid(bookrequest.getCategoryid());

        Book Booksaved =  bookRepository.save(book);
        return new BookResponse(Booksaved.getDescription(),Booksaved.getTitle());
    }
}
