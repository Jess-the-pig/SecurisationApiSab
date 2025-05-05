package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.PersonRequest;
import be.ifapme.sab.repository.PersonRepository;
import be.ifapme.sab.service.PersonService;
import be.ifapme.sab.service.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PersonController {
    private PersonService personService;
    private PersonRepository personRepository;

    public PersonController(PersonService personService, PersonRepository personRepository,TokenService tokenService) {
        this.personService = personService;
        this.personRepository = personRepository;
    }


    @PostMapping(path = "/registeruser")
    @ResponseStatus(HttpStatus.CREATED)
    private String createNewPerson(@RequestBody @Valid PersonRequest personInput) {
        personService.registerUser(personInput);
        return "User created";
    }


}
