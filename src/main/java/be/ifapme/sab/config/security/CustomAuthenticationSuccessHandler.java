package be.ifapme.sab.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // Récupère les rôles
                .filter(authority -> authority.startsWith("ROLE_")) // Filtre ceux qui commencent par ROLE_
                .findFirst()
                .orElse("");

        if ("ROLE_ADMIN".equals(role)) {
            response.sendRedirect("/admin/dashboard");
        } else if ("ROLE_CLIENT".equals(role)) {
            response.sendRedirect("/client/dashboard");
        } else {
            response.sendRedirect("/");
        }
    }

}

