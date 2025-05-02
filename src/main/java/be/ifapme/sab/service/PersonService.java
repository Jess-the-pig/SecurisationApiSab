package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.PersonRequest;
import be.ifapme.sab.api.DTO.PersonResponse;
import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.model.entities.enums.UserRole;
import be.ifapme.sab.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonService {

    private PersonRepository personRepository;

    private PasswordEncoder passwordEncoder;

    public void registerUser(PersonRequest personInput){
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
        String username = personInput.getUsername();

        //Verification si la personne est un admin.
        if(username != null && personRepository.existsByUsername(username)){
            personInput.setRole(UserRole.ADMIN);
        }
    }

    public PersonResponse getuserInfo(String email){
        Person person = personRepository.findByUsername(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        return new PersonResponse(person.getUsername(),person.getRole());
    }
}
