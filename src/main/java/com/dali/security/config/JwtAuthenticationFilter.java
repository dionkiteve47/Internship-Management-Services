package com.dali.security.config;

import com.dali.security.Repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

//classe de filtre d'authentification khater jwt authentication filter houwa ouwel haja
// fel application mte3na qui va intercepter la requete http
// et puisque ahna nhebou lfilter mte3na ykoun actif dima w a
// chaque request tji houwa y9oum b job lezem lezem naamlou extend mel
// classe OncePerRequestFilter yaani filter pour chaque requete

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter   {



    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Autowired
    private  TokenRepository  tokenRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        System.out.println("JwtAuthenticationFilter instance created");
    }
    @Override
    protected void doFilterInternal(

            @NonNull HttpServletRequest request, //C'est l'objet qui représente la requête HTTP entrante. Il contient des informations sur la requête, telles que les paramètres, les en-têtes, le corps de la requête, etc.
            @NonNull HttpServletResponse response, //C'est l'objet qui représente la réponse HTTP qui sera renvoyée au client. Vous pouvez utiliser cet objet pour définir des en-têtes de réponse, le statut de la réponse, écrire le corps de la réponse, etc.
            @NonNull  FilterChain filterChain  //C'est l'objet qui représente la chaîne de filtres à travers laquelle la requête passe. En appelant filterChain.doFilter(request, response), vous permettez à la requête actuelle de passer au filtre suivant dans la chaîne w l'ordre mte3 les filtres dima yssir fel classe SecurityConfig w dima lezem fi wosset lmétode hedhi baaed ma nkamlou logique mte3 lfiltre n7otou filterchain.doFilter(request,response);



    )

            throws ServletException, IOException {

//        System.out.println("Requête reçue pour le chemin : " + request.getServletPath());System.out.println("Dans doFilterInternal, chemin de la requête : " + request.getServletPath());



            final String authHeader = request.getHeader("Authorization");//authorization heya l'entete eli fiha token mte3na
            final String jwt; // pour le check jtw token
            final String userEmail;
        System.out.println("Method: " + request.getMethod());
        System.out.println("URL: " + request.getRequestURL());

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println("requete : "+headerName + ": " + headerValue);
        }
            if (authHeader == null || !authHeader.startsWith("Bearer "))// pour vérifier idha ken authheader feregh wela lee w yebda b bearer khater les token lkol yabdew b kelmet Bearer w baaed fama espace
                {
                    System.out.println("Pas de token, passer au filtre suivant.");


                    filterChain.doFilter(request, response);

                    return;
                } else {
                    System.out.println("Token détecté : " + authHeader);
                }


            jwt = authHeader.substring(7);//pour extraire le jwt token de authHeader et pourquoi 7? khater bearer+espace yjiw 7
            userEmail = jwtService.extractUsername(jwt);



        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null ) //bch nthabtou idha user authentifié dans le contexte de sécurité actuelle , idha ken ey c pas la peine bch nab9aw kol mara n3awdou f nafess lprocessus , nwaliw direct net3adew lel dispatcherServlet
            {

log.info("je suis la 1");


                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                System.out.println("hello"+tokenRepository.findByToken(jwt).map(t->!t.isExpired() && !t.isRevoked()));
                var isTokenValid=tokenRepository.findByToken(jwt)
                        .map(t->!t.isExpired() && !t.isRevoked())
                        .orElse(false);
                if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }

            }
        else
        {
            log.info("je suis la ");
        }



        filterChain.doFilter(request,response);

    }
}
