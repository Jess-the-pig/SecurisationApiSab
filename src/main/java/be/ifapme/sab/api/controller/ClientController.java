package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.ClientDTO;
import be.ifapme.sab.api.DTO.PanierDTO;
import be.ifapme.sab.model.entities.Admin;
import be.ifapme.sab.model.entities.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    //Connexion

    @PostMapping("/connectClient")
    public void connectClient(@RequestBody Client client){

    }

    /*
    @GetMapping("/profile")
    public ClientDTO getClientInfo(){

    }

    @GetMapping("/panier")
    public PanierDTO getClientPanier(){
//Recherche avec l'id
    }




    //Commande
        //Post commande

    //Panier
        //Put
        //Get
        //Delete
        //service GET /article/all
        //service GET /article

    //Acces commandes
        //service GET /command/all
        //service GET /command/{id}

    //Commande : ID, Code fonctionnel, Statut,
    */

}
