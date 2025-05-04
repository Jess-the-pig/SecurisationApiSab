package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.BookRequest;
import be.ifapme.sab.api.DTO.BookResponse;
import be.ifapme.sab.api.utils.SecurityUtils;
import be.ifapme.sab.model.entities.Book;
import be.ifapme.sab.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    //Rechercher tous les livres.
    public List<BookResponse> getAllBooks(){
        log.info("Recherche des livres");
        List<Book> booklist = bookRepository.findAll();
        return booklist.stream()
                .map(book -> new BookResponse(
                        book.getTitle(),
                        book.getIsbn(),
                        book.getDescription(),
                        book.getPhoto(),
                        book.getPrice(),
                        book.getQuantity(),
                        book.getCategoryid()
                ))
                .collect(Collectors.toList());
    }

    //Enregistrer un livre.
    @PreAuthorize("hasRole('ADMIN')")
    public BookResponse store(BookRequest bookrequest){
        log.info("Insertion de livre");
        SecurityUtils.checkAdmin();

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

    //Find by id
    public BookResponse getBookbyId(Long id){
        log.info("Recherche du livre");
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            return new BookResponse(
                    book.getTitle(),
                    book.getDescription()
            );
        }else{
            log.error("Livre avec ID " + id + " introuvable");
            throw new EntityNotFoundException("Livre avec ID " + id + " introuvable");
        }
    }

}
