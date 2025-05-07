package be.ifapme.sab.config.security;

import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.model.entities.enums.UserRole;
import be.ifapme.sab.repository.OAuthRepository;
import be.ifapme.sab.repository.PersonRepository;
import be.ifapme.sab.service.TokenService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    private final PersonRepository personRepository;
    private final RsaKeyProperties rsaKeyProperties;

    public SpringSecurityConfig(PersonRepository personRepository, RsaKeyProperties rsaKeyProperties) {
        this.personRepository = personRepository;
        this.rsaKeyProperties = rsaKeyProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                           AuthenticationSuccessHandler successHandler) throws Exception {

        httpSecurity.csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(antMatcher("/swagger-ui/**"),
                                antMatcher("/swagger-ui.html"),
                                antMatcher("/v3/**"),
                                antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/books")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/books/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.POST, "/registeruser")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/user/**")).hasRole(UserRole.USER.name())
                        .requestMatchers(antMatcher(HttpMethod.POST, "/user/**")).hasRole(UserRole.USER.name())
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/user/**")).hasRole(UserRole.USER.name())
                        .requestMatchers(antMatcher(HttpMethod.GET, "/admin/**")).hasRole(UserRole.ADMIN.name())
                        .requestMatchers(antMatcher(HttpMethod.POST, "/admin/**")).hasRole(UserRole.ADMIN.name())
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/admin/**")).hasRole(UserRole.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/user", true)
                        .successHandler(successHandler))
                .formLogin(form -> form.defaultSuccessUrl("/user", true))
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.removeIf(converter -> converter instanceof MappingJackson2XmlHttpMessageConverter);
        }
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
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.rsaPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() throws Exception {
        RSAPublicKey publicKey = rsaKeyProperties.rsaPublicKey();
        RSAPrivateKey privateKey = rsaKeyProperties.rsaPrivateKey();

        if (publicKey == null || privateKey == null) {
            throw new IllegalArgumentException("Public and/or private key not found.");
        }

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .build();

        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(rsaKey));
        return new NimbusJwtEncoder(jwkSource);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler(TokenService tokenService,
                                                                           OAuthRepository oAuthRepository) {
        return new OAuth2LoginSuccessHandler(tokenService, oAuthRepository);
    }
}
