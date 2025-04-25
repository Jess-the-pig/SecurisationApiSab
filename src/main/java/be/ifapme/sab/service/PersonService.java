package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.PersonRequest;
import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.model.entities.enums.UserRole;
import be.ifapme.sab.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonService {

    private PersonRepository personRepository;

    private PasswordEncoder passwordEncoder;

    public void registerUser(PersonRequest personInput){
        String email = personInput.getEmail();
        if(email != null && !personRepository.existsByEmail(email)){
            Person user = new Person();
            user.setEmail(email);
            user.setPasswordHash(passwordEncoder.encode(personInput.getPassword()));
            user.setRole(UserRole.USER);

            personRepository.save(user);
        }
    }

    public void createAdmin(PersonRequest personInput){
        String email = personInput.getEmail();

        //Verification si la personne est un admin.
        if(email != null && personRepository.existsByEmail(email)){
            personInput.setRole(UserRole.ADMIN);

        }
    }
}
