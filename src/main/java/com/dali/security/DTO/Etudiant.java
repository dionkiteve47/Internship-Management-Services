package com.dali.security.DTO;

import com.dali.security.Entity.Classe;
import com.dali.security.Entity.Preferences;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor

@NoArgsConstructor
public class Etudiant {
    private String nom;
    private String prenom;
    private String email;

    private  Classe classe;
    private boolean etat;



}