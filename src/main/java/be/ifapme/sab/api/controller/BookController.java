package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.BookResponse;
import be.ifapme.sab.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping(path = "/books")
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/books/{id}")
    public BookResponse bookById(@PathVariable @Valid Long id) {
      return bookService.getBookbyId(id);
    }



}
