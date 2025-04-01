package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.ArticleDTO;
import be.ifapme.sab.model.entities.Article;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {
    //Creation
        //Post données de creation.
    //->  titre, code ISBN unique, description, photo, prix, quantité, catégorie(1 ou plus)
    //Transaction
        //Nombre d'articles
    //Payement non anulable lorsque il est en cours.

    //Création article
    @PostMapping("/create")
    public void addArticle(@RequestBody Article article){

    }

    @PutMapping("/modify")
    public void modifyArticle(@RequestBody Article article, Integer id){

    }

    @DeleteMapping("/delete")
    public void deleteArticle(@RequestBody Article article){

    }
/*
    @GetMapping("")
    public ArticleDTO getAllArticles(){
        //Mettre en place le retour
    }

    @GetMapping("{id}")
    public ArticleDTO getAllArticles(@PathVariable String id){
        //Mettre en place le retour
    }*/
}
