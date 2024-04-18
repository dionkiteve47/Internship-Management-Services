package com.dali.security.Service;

import com.dali.security.DTO.*;
import jakarta.mail.MessagingException;

import java.util.List;

public interface IAuthenticationService {
    public RegisterResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public void bloquerUser(String email);
    public List<Etudiant> afficherEtudiants();
    public List<Enseignant> afficherEnseignants();
    public List<Representant> afficherRepresentants();
    public ChangePasswordResponse changePassword(ChangePasswordRequest request);
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) throws MessagingException;
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request);
    public AuthenticationResponse verify(VerifyRequest request);


}
