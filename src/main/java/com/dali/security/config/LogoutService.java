package com.dali.security.config;

import com.dali.security.Repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");//authorization heya l'entete eli fiha token mte3na
         final String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer "))// pour vérifier idha ken authheader feregh wela lee w yebda b bearer khater les token lkol yabdew b kelmet Bearer w baaed fama espace
        {
            System.out.println("Pas de token, passer au filtre suivant.");
            return;

        }
        jwt=authHeader.substring(7);
        var token=tokenRepository.findByToken(jwt).orElse(null);
        if(token!=null )
        {
            token.setRevoked(true);
            token.setExpired(true);
            tokenRepository.save(token);
        }

    }
}
