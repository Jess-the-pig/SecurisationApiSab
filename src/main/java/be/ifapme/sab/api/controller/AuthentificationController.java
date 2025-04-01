package be.ifapme.sab.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {

    @GetMapping("/")
    public String home(){
        return "Hello home";
    }

    @GetMapping("/secure")
    public String secured(){
        return "Hello secured";
    }
}
