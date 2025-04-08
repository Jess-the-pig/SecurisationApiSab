package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.ClientDTO;
import be.ifapme.sab.model.entities.Admin;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    //Connexion => Utiliser plutot OAUTH2.
    @PostMapping("")
    public void connectAdmin(@RequestBody Admin admin){

    }

    @PostMapping("/console")
    public String console(){
        return "Vers consoleH2";
    }

/*
    @GetMapping("")
    public ClientDTO getAllClients(){
        return ClientDTO;
    }

*/
}
