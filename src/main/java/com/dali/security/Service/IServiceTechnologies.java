package com.dali.security.Service;

import com.dali.security.Entity.Preferences;
import com.dali.security.Entity.Technologies;

import java.util.List;

public interface IServiceTechnologies {
    Technologies ajouterTechnologies(Technologies t);
    List<Technologies> afficherTechnologies();

    Technologies retriveTechnologies(Integer id);

    Technologies modifierTechnologies(Technologies t);

    void SupprimerTechnologies(Technologies t);
    List<Technologies> addAll(List<Technologies> technologies);

}
