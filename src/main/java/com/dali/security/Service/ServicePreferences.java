package com.dali.security.Service;

import com.dali.security.Entity.Preferences;
import com.dali.security.Repository.PreferencesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicePreferences implements IServicePreferences{

    PreferencesRepository preferencesRepository;

    @Override
    public Preferences ajouterPrefernces(Preferences p) {
        return preferencesRepository.save(p);
    }

    @Override
    public List<Preferences> afficherPrefernces() {
        return preferencesRepository.findAll();
    }

    @Override
    public Preferences retrivePrefernces(Integer id) {
        return preferencesRepository.findById(id).orElse(null);
    }

    @Override
    public Preferences modifierPrefernces(Preferences p) {
        return preferencesRepository.save(p);
    }

    @Override
    public void SupprimerPrefernces(Preferences p) {
        preferencesRepository.delete(p);

    }

    @Override
    public List<Preferences> addAll(List<Preferences> preferences) {
        return preferencesRepository.saveAll(preferences);
    }
}
