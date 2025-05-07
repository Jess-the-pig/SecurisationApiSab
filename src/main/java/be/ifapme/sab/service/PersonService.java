package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.PersonRequest;
import be.ifapme.sab.api.DTO.PersonResponse;
import be.ifapme.sab.api.utils.SecurityUtils;
import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.model.entities.enums.UserRole;
import be.ifapme.sab.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class PersonService {

    private PersonRepository personRepository;

    private PasswordEncoder passwordEncoder;


    public void registerUser(PersonRequest personInput){
        log.debug("Enregistrement utilisateur");
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
        log.debug("Creation d'un admin");
        String username = personInput.getUsername();

        if(username != null && personRepository.existsByUsername(username)){
            personInput.setRole(UserRole.ADMIN);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public PersonResponse getuserInfo(String email){
        log.debug("Recherche le l'information utilisateur");
        SecurityUtils.checkAdmin();
        Person person = personRepository.findByUsername(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        return new PersonResponse(person.getUsername(),person.getRole());
    }

    @PreAuthorize("hasRole('USER')")
    public PersonResponse getthisuserInfo(){
        log.debug("Recherche de l'information utilisateur");
        SecurityUtils.checkUser();

        // Récupère le nom d'utilisateur à partir du token JWT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // souvent l'email ou le sub du token

        // Vérifie l'utilisateur
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return new PersonResponse(person.getUsername(), person.getRole());
    }
}
