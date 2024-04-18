package com.dali.security.Service;

import com.dali.security.Entity.Demandes;
import com.dali.security.Entity.OffresStages;
import com.dali.security.Entity.Technologies;
import com.dali.security.Repository.OffreRepository;
import com.dali.security.Repository.TechRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Slf4j
@Service
public class ServiseOffreS implements IServiceOffreS {

     OffreRepository offreRepository;

    TechRepository technologiesRepository;

    @Override
    public OffresStages ajouterOffresStage(OffresStages offresStages) {
        return  offreRepository.save(offresStages);
    }

    @Override
    public OffresStages creatOffresStage(OffresStages offre, Set<Long> technologyIds) {
        for (Long techId : technologyIds) {
            Technologies technology = technologiesRepository.findById(techId).orElse(null);
            if (technology != null) {
                offre.getTechnologiess().add(technology);
            }
        }
        return offreRepository.save(offre);
    }


   //public OffresStages creatOffresStage(OffresStages offresStages) {
        // Convert technology IDs to Technology entities and set them to offresStages
        //Set<Technologies> technologies = offresStages.getTechnologiess();
        //for (Technologies technology : technologies) {
         //   Long id = technology.getId();
        //}


       // return offreRepository.save(offresStages);
    //}

    @Override
    public OffresStages updateOffresStage(OffresStages offresStages) {
        Long id = offresStages.getIdoff();
        if (offreRepository.existsById(id)) {
            return offreRepository.save(offresStages);
        } else {

            return null;
        }
    }
    public OffresStages updateRating(Long id, int newRating) {
        Optional<OffresStages> optionalOffre = offreRepository.findById(id);

        if (optionalOffre.isPresent()) {
            OffresStages offresStages = optionalOffre.get();
            offresStages.setStarRating(newRating);
            return offreRepository
                    .save(offresStages);
        }

        return null;
    }
    @Override
    public List<OffresStages> findByEtat(String etat) {
        return offreRepository.findByEtat(etat);
    }
    @Override
    public OffresStages getOffresStageById(long id) {
       return offreRepository.findById(id).orElse(null);
    }

    @Override
    public List<OffresStages> getAllOffresStage() {
        return (List<OffresStages>) offreRepository.findAll();
    }

    @Override
    public void deleteOffresStage(long id) {
        offreRepository.deleteById(id);

    }
    @Override
    public OffresStages ajouterdemandes(OffresStages offresStages) {
        offresStages.setEtat("En Court");
        return offreRepository.save(offresStages);
    }
    @Transactional
    public void archiverOffre(long id) {
        OffresStages offresStagese = offreRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        offresStagese.setEtat("Archiver");
        offreRepository.save(offresStagese);
    }
}
