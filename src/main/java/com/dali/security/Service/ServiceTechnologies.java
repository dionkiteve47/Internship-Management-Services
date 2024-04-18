package com.dali.security.Service;

import com.dali.security.Entity.Technologies;
import com.dali.security.Repository.TechnologiesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceTechnologies implements IServiceTechnologies{
    TechnologiesRepository technologiesRepository;

    @Override
    public Technologies ajouterTechnologies(Technologies t) {
        return technologiesRepository.save(t);
    }

    @Override
    public List<Technologies> afficherTechnologies() {
        return technologiesRepository.findAll();
    }

    @Override
    public Technologies retriveTechnologies(Integer id) {
        return technologiesRepository.findById(id).orElse(null);
    }

    @Override
    public Technologies modifierTechnologies(Technologies t) {
        return technologiesRepository.save(t);
    }

    @Override
    public void SupprimerTechnologies(Technologies t) {
        technologiesRepository.delete(t);

    }

    @Override
    public List<Technologies> addAll(List<Technologies> technologies) {
        return technologiesRepository.saveAll(technologies);
    }
}
