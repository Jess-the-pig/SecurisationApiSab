package be.ifapme.sab.config.security;

import be.ifapme.sab.model.entities.OAuthPerson;
import be.ifapme.sab.model.entities.enums.UserRole;
import be.ifapme.sab.repository.OAuthRepository;
import be.ifapme.sab.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final OAuthRepository oAuthRepository;

    public OAuth2LoginSuccessHandler(TokenService tokenService, OAuthRepository oAuthRepository) {
        this.tokenService = tokenService;
        this.oAuthRepository = oAuthRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauthUser = oauthToken.getPrincipal();
        String email = oauthUser.getAttribute("email");

        if (email != null && !oAuthRepository.existsByEmail(email)) {
            OAuthPerson user = new OAuthPerson();
            user.setEmail(email);
            user.setRole(UserRole.USER);
            oAuthRepository.save(user);
        }

        String token = tokenService.generateToken(authentication);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
        response.getWriter().flush();
    }
}
