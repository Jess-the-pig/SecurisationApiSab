package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.BookRequest;
import be.ifapme.sab.api.DTO.BookResponse;
import be.ifapme.sab.model.entities.Book;
import be.ifapme.sab.repository.BookRepository;
import be.ifapme.sab.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final BookRepository bookRepository;
    private final BookService bookService;


    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping(path = "/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(path = "/books/{id}")
    public Book bookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + " not available");
        }

        return bookOptional.get();
    }

    @PostMapping(path = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "BasicAuth")
    public BookResponse createBook(@RequestBody @Valid BookRequest newBookRequest) {
        return bookService.store(newBookRequest);
    }
}
