package com.dali.security.config;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.ALWAYS;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration {


     @Autowired
    private final AuthenticationProvider authenticationProvider;
    @Autowired
    private final JwtAuthenticationFilter jwtAuthFilter;

    private  final LogoutHandler logoutHandler;




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.cors().and().csrf().disable()

                        .authorizeRequests(req->req.requestMatchers("/intership/api/v1/auth/authenticate","/intership/api/v1/auth/register","api/v1/auth/forgotPassword","api/v1/auth/resetPassword","api/v1/auth/confirm","api/v1/auth/verify")
                        .permitAll()
                        .anyRequest().authenticated()
                        )


                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                        .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout") //spring yestaamel /logout comme url par défaut , a7na haka 9olna lspring ki tji urk /api/v1/auth/logout executeha enti w ma ta3teha lhata controller ekher
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()));






        return http.build();

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:61723","http://localhost:4201")); // Autoriser l'origine spécifique
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Autoriser les méthodes HTTP
        configuration.setAllowedHeaders(Arrays.asList("*")); // Autoriser les en-têtes spécifiques

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
