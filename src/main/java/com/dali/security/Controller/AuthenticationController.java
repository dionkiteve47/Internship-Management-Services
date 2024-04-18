package com.dali.security.Controller;

import com.dali.security.DTO.*;
import com.dali.security.Service.AuthenticationService;
import com.dali.security.config.JwtService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final AuthenticationService service;
    @Autowired
    private  final JwtService j;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        var response = service.register(request);

        if ("Succesfully".equals(response.getMessage())) {
            if(response.isMfaEnabled()) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.accepted().build();

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(

            @RequestBody AuthenticationRequest request
    ) {
        System.out.println("Login"+request.getPassword());
        String message=service.authenticate(request).getMessage();
        if("Succesfully".equals(message))
        {
            return ResponseEntity.ok(service.authenticate(request));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(service.authenticate(request));
        }


    }
    @PostMapping("/verify")
    public  ResponseEntity<?> verify(
            @RequestBody VerifyRequest request
    )
    {
       return  ResponseEntity.ok(service.verify(request));
    }


    /*@GetMapping("/reclamations")
    public String dali(@AuthenticationPrincipal UserDetails userDetails)
    {

        return userDetails.getUsername();
    }*/

    @PostMapping("/BloquerOrDeboloque/{email}")
    public void bloquerUser(@PathVariable String email)
    {
        service.bloquerUser(email);
    }

    @GetMapping("/allEtudiants")
    public List<Etudiant> allEtudiant ()
    {
        System.out.println(service.afficherEtudiants());
        return service.afficherEtudiants();

    }
    @GetMapping("/allEnseignants")
    public List<Enseignant> allEnseignants ()
    {
        return service.afficherEnseignants();
    }
    @GetMapping("/allRepresentants")
    public List<Representant> allRepresentants ()
    {
        return service.afficherRepresentants();
    }
    @PostMapping("/changePassword")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request)
    {
        ChangePasswordResponse response=service.changePassword(request);
        if("Succesfully".equals(response.getMessage()))
        {
            return ResponseEntity.ok(response);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PostMapping("/forgotPassword")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) throws MessagingException {
        ForgotPasswordResponse response=service.forgotPassword(request);
        if("Succesfully".equals(response.getMessage()))
        {
            return ResponseEntity.ok(response);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<ResetPasswordResponse> resetPassword(@RequestBody ResetPasswordRequest request)
    {
        System.out.println("Reset"+request);

        ResetPasswordResponse response=service.resetPassword(request);
        if("Succesfully".equals(response.getMessage()))
        {
            return ResponseEntity.ok(response);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @GetMapping(path = "confirm")
    public Map<String,String> confirm(@RequestParam("token") String token) {
        return service.confirmToken(token);
    }


}
