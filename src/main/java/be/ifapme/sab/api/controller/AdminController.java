package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.BookRequest;
import be.ifapme.sab.api.DTO.BookResponse;
import be.ifapme.sab.api.controller.exceptions.TooManyRequestsException;
import be.ifapme.sab.api.controller.utils.BandwidthLimiter;
import be.ifapme.sab.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final BookService bookService;
    private final BandwidthLimiter bandwidthLimiter;


    public AdminController(BookService bookService,BandwidthLimiter bandwidthLimiter) {
        this.bandwidthLimiter=bandwidthLimiter;
        this.bookService = bookService;
    }


    @PostMapping(path = "/book")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "BasicAuth")
    public BookResponse createBook(@RequestBody @Valid BookRequest newBookRequest) {
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
        return bookService.store(newBookRequest);
    }


}
