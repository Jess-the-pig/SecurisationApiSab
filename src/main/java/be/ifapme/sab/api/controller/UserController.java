package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.*;
import be.ifapme.sab.api.controller.exceptions.TooManyRequestsException;
import be.ifapme.sab.api.controller.utils.BandwidthLimiter;
import be.ifapme.sab.model.entities.CustomUserDetails;
import be.ifapme.sab.service.CartService;
import be.ifapme.sab.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER')")
public class UserController {
    private PersonService personService;
    private CartService cartService;
    private BandwidthLimiter bandwidthLimiter;


    @GetMapping(path = "/profile")
    @ResponseStatus(HttpStatus.FOUND)
    public PersonResponse consultProfile(Principal principal) {
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
        return personService.getuserInfo(principal.getName());
    }

    @PostMapping(path = "/cart/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItemtoCard(@RequestBody @Valid BookRequest bookRequest) {
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
       cartService.storeItem(bookRequest);

    }

    @PostMapping(path="/cart")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCart(@AuthenticationPrincipal CustomUserDetails userDetails){
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
        cartService.createCart(userDetails);
    }

    @GetMapping(path = "/cart/items")
    @ResponseStatus(HttpStatus.FOUND)
    public List<CartItemResponse> SeeAllItems(@RequestBody @Valid CartRequest cartRequest) {
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
        return cartService.seeAllItems(cartRequest);
    }

    @GetMapping(path = "/cart/items/{itemId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<CartItemResponse> SeeIteminCart(@PathVariable Long itemId, @AuthenticationPrincipal CustomUserDetails userDetails){
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
        return cartService.seeIteminCart(itemId,userDetails);
    }

    @DeleteMapping(path = "cart/items/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteItemFromCart(@PathVariable Long itemId, @AuthenticationPrincipal CustomUserDetails userDetails){
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
        cartService.deleteItemFromCart(itemId,userDetails);
    }

    @PostMapping(path = "cart/checkout")
    public ResponseEntity<?> checkout(@RequestParam Long cartId){
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
        return cartService.processPayementandMakeOrder(cartId);
    }







}
