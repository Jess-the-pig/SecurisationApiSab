package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.PersonRequest;
import be.ifapme.sab.api.controller.exceptions.TooManyRequestsException;
import be.ifapme.sab.api.controller.utils.BandwidthLimiter;
import be.ifapme.sab.service.PersonService;
import be.ifapme.sab.service.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PersonController {
    private PersonService personService;
    private BandwidthLimiter bandwidthLimiter;

    public PersonController(PersonService personService,TokenService tokenService,BandwidthLimiter bandwidthLimiter) {
        this.bandwidthLimiter=bandwidthLimiter;
        this.personService = personService;
    }


    @PostMapping(path = "/registeruser")
    @ResponseStatus(HttpStatus.CREATED)
    private String createNewPerson(@RequestBody @Valid PersonRequest personInput) {
        if (bandwidthLimiter.tryConsume()) {
            throw new TooManyRequestsException("Trop de requêtes, réessaie plus tard.");
        }
        personService.registerUser(personInput);
        return "User created";
    }


}
