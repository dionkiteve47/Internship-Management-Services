package com.dali.security.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor

@NoArgsConstructor
public class Representant {
    private String nom;
    private String prenom;
    private String email;

    String nom_entreprise;
    private boolean etat;


}
