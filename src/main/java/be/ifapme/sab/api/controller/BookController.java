package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.BookResponse;
import be.ifapme.sab.api.controller.exceptions.TooManyRequestsException;
import be.ifapme.sab.api.controller.utils.BandwidthLimiter;
import be.ifapme.sab.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;
    private final BandwidthLimiter bandwidthLimiter;

    public BookController(BookService bookService, BandwidthLimiter bandwidthLimiter) {
        this.bookService = bookService;
        this.bandwidthLimiter=bandwidthLimiter;
    }


    @GetMapping(path = "/books")
    public List<BookResponse> getAllBooks() {
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/books/{id}")
    public BookResponse bookById(@PathVariable @Valid Long id) {
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
        return bookService.getBookbyId(id);
    }



}
