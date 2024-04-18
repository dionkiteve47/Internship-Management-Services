package com.example.internshipmanagement.services.Imlp;

import com.example.internshipmanagement.entities.Formation;
import com.example.internshipmanagement.entities.Support;
import com.example.internshipmanagement.repositories.*;
import com.example.internshipmanagement.services.Interface.SupportInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupportService implements SupportInterface {

    FormationRepository formationRepository;
    SupportRepository supportRepository;
    VideoRepository videoRepository;
    QuizzRepository quizzRepository;
    QuestionRepository questionRepository;

    @Override
    public Support addSupport(Support s) {
        return supportRepository.save(s);
    }

    @Override
    public Support updateSupport(Support s) {
        supportRepository.save(s);
        return s;
    }

    @Override
    public List<Support> retrieveAllSupport() {
        return supportRepository.findAll();
    }

    @Override
    public Support retrieveSupportByID(Long id) {
        return supportRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSupport(Long id) {
        supportRepository.deleteById(id);
    }

    //*******************AVANCEES*****************//

    @Override
    public Support AddSupportAndAssign(Support support, long id) {
        Formation formation = formationRepository.findById(id).get();
        support.setFormationsupport(formation);
        return supportRepository.save(support);

    }


}
