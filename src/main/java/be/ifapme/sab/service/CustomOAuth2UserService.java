package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.PersonInput;
import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.model.entities.UserRole;
import be.ifapme.sab.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private PersonRepository personRepository;

    @Override
    @SneakyThrows
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        log.trace("Load user {}", oAuth2UserRequest);
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        PersonInput userInfoDto = Oauth2UserInfoDto
                .builder()
                .name(oAuth2User.getAttributes().get("name").toString())
                .id(oAuth2User.getAttributes().get("sub").toString())
                .email(oAuth2User.getAttributes().get("email").toString())
                .picture(oAuth2User.getAttributes().get("picture").toString())
                .build();

        log.trace("User info is {}", userInfoDto);
        Optional<Person> userOptional = personRepository.findByUsername(userInfoDto.getEmail());
        log.trace("User is {}", userOptional);

        Person user = userOptional
                .map(existingUser -> updateExistingUser(existingUser, userInfoDto))
                .orElseGet(() -> registerNewUser(oAuth2UserRequest, userInfoDto));
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private Person registerNewUser(OAuth2UserRequest oAuth2UserRequest, PersonInput userInfoDto) {
        Person user = new Person();
        user.setUsername(userInfoDto.getEmail());

        return personRepository.save(user);
    }



}
