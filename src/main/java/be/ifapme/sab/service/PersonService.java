package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.PersonRequest;
import be.ifapme.sab.api.DTO.PersonResponse;
import be.ifapme.sab.api.utils.SecurityUtils;
import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.model.entities.enums.UserRole;
import be.ifapme.sab.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonService {

    private PersonRepository personRepository;

    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    public void registerUser(PersonRequest personInput){
        logger.info("Enregistrement utilisateur");
        String username = personInput.getUsername();
        if(username != null && !personRepository.existsByUsername(username)){
            Person user = new Person();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(personInput.getPassword()));
            user.setRole(UserRole.USER);

            personRepository.save(user);
        }
    }

    public void createAdmin(PersonRequest personInput){
        logger.info("Creation d'un admin");
        String username = personInput.getUsername();

        if(username != null && personRepository.existsByUsername(username)){
            personInput.setRole(UserRole.ADMIN);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public PersonResponse getuserInfo(String email){
        logger.info("Recherche le l'information utilisateur");
        SecurityUtils.checkAdmin();
        Person person = personRepository.findByUsername(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        return new PersonResponse(person.getUsername(),person.getRole());
    }
}
