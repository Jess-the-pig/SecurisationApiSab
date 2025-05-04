package be.ifapme.sab.service;

import be.ifapme.sab.model.entities.CustomUserDetails;
import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomUserDetailsService implements UserDetailsService {
    private PersonRepository personRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Recherches des information utilisateur");
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new CustomUserDetails(person);
    }
}
