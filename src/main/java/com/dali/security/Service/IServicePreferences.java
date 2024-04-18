package com.dali.security.Service;

import com.dali.security.Entity.Preferences;

import java.util.List;

public interface IServicePreferences {
   Preferences ajouterPrefernces(Preferences p);
   List<Preferences> afficherPrefernces();

   Preferences retrivePrefernces(Integer id);

   Preferences modifierPrefernces(Preferences p);

   void SupprimerPrefernces(Preferences p);

   List<Preferences> addAll(List<Preferences> preferences);


}
