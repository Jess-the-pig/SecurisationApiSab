package be.ifapme.sab.service;

import be.ifapme.sab.model.entities.CustomUserDetails;
import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Recherches des information utilisateur");
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new CustomUserDetails(person);
    }
}
