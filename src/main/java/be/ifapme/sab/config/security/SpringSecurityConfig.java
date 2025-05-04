package be.ifapme.sab.config.security;

import be.ifapme.sab.model.entities.OAuthPerson;
import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.model.entities.enums.UserRole;
import be.ifapme.sab.repository.OAuthRepository;
import be.ifapme.sab.repository.PersonRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SpringSecurityConfig {

    private OAuthRepository oAuthRepository;
    private PersonRepository personRepository;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(antMatcher("/swagger-ui/**"),
                                    antMatcher("/swagger-ui.html"),
                                    antMatcher("/v3/**"),
                                    antMatcher("/h2-console/**")).permitAll()
                            .requestMatchers(antMatcher(HttpMethod.GET, "/books")).permitAll()
                            .requestMatchers(antMatcher(HttpMethod.POST, "/books")).permitAll()
                            .requestMatchers(antMatcher(HttpMethod.GET, "/books/**")).permitAll()
                            .requestMatchers(antMatcher(HttpMethod.POST, "/persons")).permitAll()
                            .requestMatchers(antMatcher(HttpMethod.POST, "/registeruser")).permitAll()

                            .requestMatchers(antMatcher(HttpMethod.GET, "/users/**")).hasRole(UserRole.USER.name())
                            .requestMatchers(antMatcher(HttpMethod.GET, "/admin/**")).hasRole(UserRole.ADMIN.name())

                            .anyRequest().authenticated();

                })
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/secure", true)
                        .successHandler(oAuth2AuthenticationSuccessHandler())
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/secure", true))
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Person user = personRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().name())

                    .build();
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {

                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
                OAuth2User oauthUser = oauthToken.getPrincipal();

                String email = oauthUser.getAttribute("email");

                if (email != null && !oAuthRepository.existsByEmail(email)) {
                    OAuthPerson user = new OAuthPerson();
                    user.setEmail(email);
                    user.setRole(UserRole.USER);
                    oAuthRepository.save(user);
                }

                response.sendRedirect("/secure"); // redirection après succès
            }
        };
    }


}
