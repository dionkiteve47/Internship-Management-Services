package com.dali.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor

@NoArgsConstructor
public class Enseignant {
    private String nom;
    private String prenom;
    private String email;
    private boolean etat;
}
