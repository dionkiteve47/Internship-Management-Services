package com.dali.security.DTO;

import com.dali.security.Entity.Classe;
import com.dali.security.Entity.Preferences;
import com.dali.security.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    String firstname;
    String lastname ;
    String email;
    String password ;
    Role roles;
    Classe classe;
    private boolean mfaEnabled;
    List<Preferences> preferences;

}
