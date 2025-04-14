package be.ifapme.sab.config.security;

import be.ifapme.sab.model.entities.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(antMatcher("/swagger-ui/**"),
                                    antMatcher("/swagger-ui.html"),
                                    antMatcher("/v3/**"),
                                    antMatcher("/h2-console/**")).permitAll()
                            .requestMatchers(antMatcher(HttpMethod.GET, "/books")).permitAll()
                            //Test de store
                            .requestMatchers(antMatcher(HttpMethod.POST, "/books")).permitAll()
                            .requestMatchers(antMatcher(HttpMethod.GET, "/books/**")).permitAll()
                            .requestMatchers(antMatcher(HttpMethod.POST, "/persons")).hasRole(UserRole.ADMIN.name())
                            .anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/users", true) // Redirection après connexion réussie
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/secure", true) // Redirection aussi pour login formulaire
                )
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // This so embedded frames in h2-console are working
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }




}
