package com.dali.security.Service;

import com.dali.security.DTO.*;
import com.dali.security.Entity.*;
import com.dali.security.Repository.TokenRepository;
import com.dali.security.Repository.UserRepository;
import com.dali.security.config.JwtService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationService implements  IAuthenticationService {

     EmailService emailService;

     UserRepository repo;

     PasswordEncoder passwordEncoder;

     JwtService jwtService;

     AuthenticationManager authenticationManager;

     TokenRepository t;

     IServiceTechnologies serviceTechnologies;

     IServicePreferences servicePreferences;

     IServiceVerification serviceVerification;
     TwoFactorAuthenticationService twoFactorAuthenticationService;

    @Override
     public RegisterResponse register(RegisterRequest request) {
        System.out.println(request);
        User u = repo.findByEmail(request.getEmail()).orElse(null);
        if (u != null)
        {
            String errorMessage = "User with email " + request.getEmail() + " already exists";
            return RegisterResponse.builder()
                            .message(errorMessage)
                             .build();
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .classe(request.getClasse())
                .exp_mdp(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .preferences(request.getPreferences())
                .mfaEnabled(request.isMfaEnabled())
                .build();
        if(request.isMfaEnabled())
        {
            user.setSecret(twoFactorAuthenticationService.generateNewSecret());
        }

        repo.save(user);
        System.out.println("Preference"+request.getPreferences()+"\n");
        for(Preferences p: request.getPreferences())
        {
            p.setUser(user);
            servicePreferences.ajouterPrefernces(p);
        }
        

        for(Preferences p:request.getPreferences())
        {
            for(Technologies t:p.getTechnologies())
            {

                t.setPreferences(p);
                serviceTechnologies.ajouterTechnologies(t);
            }

        }
                 var verification = Verification.builder()
                .token(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .user(user)
                .build();

              serviceVerification.ajouterVerification(verification);


        return RegisterResponse.builder()
                .message("Succesfully")
                .mfaEnabled(request.isMfaEnabled())
                .qrCode(twoFactorAuthenticationService.generateQRCode(user.getSecret()))
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token= Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        t.save(token);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            User user1 = repo.findByEmail(request.getEmail()).orElse(null);
            if (user1 == null) {
                return new AuthenticationResponse("Utilisateur non trouvé");
            } else  if (!user1.isEnabled()) {
                return new AuthenticationResponse("Compte verouille");

            } else if (!user1.isCredentialsNonExpired()) {
                return new AuthenticationResponse("Mot de passe expiré");
            } else {
                System.out.println("Compte deverouiller");
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
                if(user1.isMfaEnabled())
                {
                    return AuthenticationResponse.builder()
                            .token("")
                            .role(Role.valueOf(""))
                            .nom("")
                            .prenom("")
                            .mfaEnabled(true)
                            .message("Succesfully")
                            .build();
                }
                var jwtToken = jwtService.generateToken(user1);
                revokeAllUserTokens(user1);
                saveUserToken(user1,jwtToken);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user1.getEmail(),
                        null,
                        user1.getAuthorities()
                );
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .role(user1.getRoles())
                        .nom(user1.getFirstname())
                        .prenom(user1.getLastname())
                        .mfaEnabled(false)
                        .message("Succesfully")

                        .build();
            }
        } catch (BadCredentialsException e) {
            log.error("Erreur d'authentification", e);
           return  new AuthenticationResponse("Mot de passe incorrect");
        }


    }

    //lezem ykoun 3ana un seul token valide pour chaque user
    private void revokeAllUserTokens( User user)
    {
        var validUserToken=t.findAllValidTokensByUser(user.getId());
        if(validUserToken.isEmpty())
            return;
        validUserToken.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        t.saveAll(validUserToken);


    }

    @Override
    public void bloquerUser(String email) {
        User u = repo.findByEmail(email).orElse(null);
        if (u != null) {
            u.setEtat(!(u.isEtat()));
            repo.save(u);
        }

    }
    @Scheduled(cron = "0 25 2 * * ?")
    public void yourScheduledMethod()
    {
        Date currentDate = new Date();

// Ajoutez 3 jours à la date actuelle
        LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate threeDaysLater = localDate.plusDays(3);

     // Convertissez LocalDate en Date si nécessaire
        Date threeDaysLaterDate = Date.from(threeDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<User> users = repo.findUserByExpiredMdp(threeDaysLaterDate);
        if(!users.isEmpty()) {
            for (User u : users) {
                log.info(u.getEmail());
            }
        }
        else
            log.info("erreur");

    }
    @Override
    public List<Etudiant> afficherEtudiants()
    {
        List<Etudiant> lise=new ArrayList<>();
        List<User> users = repo.allEtudiants();
        for(User u :users)
        {
            var etudiant=Etudiant.builder()
                    .nom(u.getFirstname())
                    .prenom(u.getLastname())
                    .email(u.getEmail())
                    .classe(u.getClasse())
                    .etat(u.isEtat())
                    .build();
            lise.add(etudiant);
        }
        return lise;

    }
    @Override
    public List<Enseignant> afficherEnseignants()
    {
        List<Enseignant> lise=new ArrayList<>();
        List<User> users = repo.allEnseignants();
        for(User u :users)
        {
            var enseignant=Enseignant.builder()
                    .nom(u.getFirstname())
                    .prenom(u.getLastname())
                    .email(u.getEmail())
                    .etat(u.isEtat())
                     .build();
            lise.add(enseignant);
        }
        return lise;
    }
    @Override
    public List<Representant> afficherRepresentants()
    {
        List<Representant> lise=new ArrayList<>();
        List<User> users = repo.allRepresentants();
        for(User u :users)
        {
            var representant=Representant.builder()
                    .nom(u.getFirstname())
                    .prenom(u.getLastname())
                    .email(u.getEmail())
                    .nom_entreprise(u.getNom_entreprise())
                    .etat(u.isEtat())
                    .build();
            lise.add(representant);
        }
        return lise;
    }


    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        User user = repo.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return new ChangePasswordResponse("Utilisateur non trouvé");
        } else if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return new ChangePasswordResponse("Mot de passe incorrect");
        } else if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return new ChangePasswordResponse("Les mots de passe ne correspondent pas");
        } else {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            user.setExp_mdp(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            repo.save(user);
            return new ChangePasswordResponse("Succesfully");
        }
    }

    @Override
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) throws MessagingException {
        User user = repo.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return new ForgotPasswordResponse("Utilisateur non trouvé");
        } else {
            emailService.sendEmail(user.getEmail(), "Changement de mot de passe",
                    "Bonjour "+user.getFirstname()+"<br/>"+
                            "Pour changer votre mot de passe veuillez cliquer <a href='http://localhost:4200/reset' style='display: inline-block; padding: 10px 20px; color: #fff; background-color: #007bff; border-radius: 5px; text-decoration: none;'>Reset Password</a>");
            return new ForgotPasswordResponse("Succesfully");
        }
    }

    @Override
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {
        User user = repo.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return new ResetPasswordResponse("Utilisateur non trouvé");
        } else {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            user.setExp_mdp(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            repo.save(user);
            return new ResetPasswordResponse("Succesfully");
        }
    }

    public Map<String, String> confirmToken(String token) {
        var verification = serviceVerification.getToken(token).orElse(null);
        System.out.println(verification);
        Map<String, String> response = new HashMap<>();
        if (verification == null) {
            response.put("message", "Token non trouvé");
        } else {
            var now = LocalDateTime.now();
            serviceVerification.setConfirmedAt(token);
            repo.enableUser(verification.getUser().getEmail());
            response.put("message", "Email confirmé");
        }
        return response;
    }

    @Override
    public AuthenticationResponse verify(VerifyRequest request) {
        User user = repo.findByEmail(request.getEmail()).orElse(null);
        assert user != null;
        if(!twoFactorAuthenticationService.isOtpValid(user.getSecret(),request.getCode()))
         {
             return new AuthenticationResponse("Code incorrect");
         }
         else
         {
                var jwtToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user,jwtToken);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .role(user.getRoles())
                        .nom(user.getFirstname())
                        .prenom(user.getLastname())
                        .mfaEnabled(user.isMfaEnabled())
                        .message("Succesfully")
                        .build();
         }
    }
}
