package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.*;
import be.ifapme.sab.model.entities.CustomUserDetails;
import be.ifapme.sab.service.CartService;
import be.ifapme.sab.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private PersonService personService;
    private CartService cartService;


    @GetMapping(path = "/profile")
    private PersonResponse consultProfile(Principal principal) {
        return personService.getuserInfo(principal.getName());
    }

    @PostMapping(path = "/cart/items")
    private void addItemtoCard(@RequestBody @Valid BookRequest bookRequest) {
       cartService.storeItem(bookRequest);

    }

    @PostMapping(path="/cart")
    private void createCart(@AuthenticationPrincipal CustomUserDetails userDetails){
        cartService.createCart(userDetails);
    }

    @GetMapping(path = "/cart/items")
    private List<CartItemResponse> SeeAllItems(@RequestBody @Valid CartRequest cartRequest) {
        return cartService.seeAllItems(cartRequest);
    }

    @GetMapping(path = "/cart/items/{itemId}")
    private ResponseEntity<CartItemResponse> SeeIteminCart(@PathVariable Long itemId, @AuthenticationPrincipal CustomUserDetails userDetails){
        return cartService.seeIteminCart(itemId,userDetails);
    }

    @DeleteMapping(path = "cart/items/{itemId}")
    private void DeleteItemFromCart(@PathVariable Long itemId, @AuthenticationPrincipal CustomUserDetails userDetails){
        cartService.deleteItemFromCart(itemId,userDetails);
    }






}
