package com.example.intershipmanagement.services.Imlp;

import com.example.intershipmanagement.entities.Formation;
import com.example.intershipmanagement.repositories.FormationRepository;
import com.example.intershipmanagement.services.Interface.FormationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormationService implements FormationInterface {

    //Injection
    FormationRepository formationRepository;
    @Override
    public Formation addFormation(Formation f) {
        return formationRepository.save(f);
    }

    @Override
    public Formation updateFormation(Formation f) {
        return formationRepository.save(f);
    }


    @Override
    public Formation retriveFormationByID(Long id) {
        return formationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFormation(Long id) {
         formationRepository.deleteById(id);
    }

    @Override
    public List<Formation> retriveAllFormation() {
       return formationRepository.findAll();
    }

}
