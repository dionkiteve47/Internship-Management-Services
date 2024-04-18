package com.dali.security.DTO;

import com.dali.security.Entity.Preferences;
import com.dali.security.Entity.Role;
import com.dali.security.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Role role;
    private String nom;
    private String  prenom;
    private  boolean mfaEnabled;
    private String message;
    private List<Preferences> preferencesList;

    public AuthenticationResponse(String message) {
        this.message = message;
    }
}
