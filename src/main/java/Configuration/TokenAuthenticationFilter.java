package Configuration;

import Services.InMemoryAuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private InMemoryAuthService inMemoryAuthService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Exemption des routes publiques
        String requestPath = request.getRequestURI();
        if (requestPath.contains("/auth/login") ||
                requestPath.contains("/auth/test") ||
                requestPath.contains("/admins") && request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Vérification du token d'authentification
        String authToken = request.getHeader("Authorization");

        if (authToken != null && inMemoryAuthService.validateToken(authToken)) {
            // Token valide, continuer la chaîne de filtres
            filterChain.doFilter(request, response);
        } else {
            // Token invalide ou absent, renvoyer une erreur 401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Authentification requise\"}");
        }
    }
}