package be.ifapme.sab.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {

    @GetMapping("/user")
    public String home(){
        return "Welcome user";
    }

    @GetMapping("/admin")
    public String secured(){
        return "Welcome Admin";
    }
}
