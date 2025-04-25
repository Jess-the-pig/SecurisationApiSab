package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.PersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping(path = "/profile")
    private UserResponse consultProfile() {
        return UserResponse;
    }

    @PostMapping(path = "/cart/items")
    private UserCardResponse addItemtoCard() {
        return UserCardResponse;
    }

    @GetMapping(path = "/cart")
    private UserCardResponse SeeAllItems() {
        return UserCardResponse;
    }

    @GetMapping(path = "/cart/items/{itemId}")
    private UserCardResponse SeeIteminCard(){
        return UserCardResponse;
    }

    @DeleteMapping(path = "cart/items/{itemId}")
    private UserCardResponse DeleteItemFromCart(){
        return UserCardResponse;
    }






}
