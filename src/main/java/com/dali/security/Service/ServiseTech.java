package com.dali.security.Service;

import com.dali.security.Entity.Demandes;
import com.dali.security.Entity.OffresStages;
import com.dali.security.Entity.Technologies;
import com.dali.security.Repository.OffreRepository;
import com.dali.security.Repository.TechRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Slf4j
@Service
public class ServiseTech implements ITechS{
    TechRepository techRepository;
    @Override
    public Technologies ajouterITechS(Technologies tech) {
            return  techRepository.save(tech);}

    @Override
    public Technologies updateITechS(Technologies tech) {
        Integer id = tech.getId();
        if (techRepository.existsById(Long.valueOf(id))) {
            return techRepository.save(tech);
        } else {

            return null;
        }
    }

    @Override
    public Technologies getITechSById(long id) {
        return techRepository.findById(id).orElse(null);
    }

    @Override
    public List<Technologies> getAllITechS() {
        return (List<Technologies>) techRepository.findAll();
    }


    @Override
    public void deleteITechS(long id) {
        techRepository.deleteById(id);

    }
}
