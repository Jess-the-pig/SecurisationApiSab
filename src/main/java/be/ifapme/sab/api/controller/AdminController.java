package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.BookRequest;
import be.ifapme.sab.api.DTO.BookResponse;
import be.ifapme.sab.repository.BookRepository;
import be.ifapme.sab.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final BookService bookService;


    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path = "/book")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "BasicAuth")
    public BookResponse createBook(@RequestBody @Valid BookRequest newBookRequest) {
        return bookService.store(newBookRequest);
    }


}
