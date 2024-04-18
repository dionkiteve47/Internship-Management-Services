package com.dali.security.Service;

import com.dali.security.Entity.OffresStages;

import java.util.List;
import java.util.Set;

public interface IServiceOffreS {

     OffresStages ajouterOffresStage(OffresStages offresStages);
     OffresStages updateOffresStage(OffresStages offresStages);
     OffresStages getOffresStageById(long id);
     List<OffresStages> getAllOffresStage();
     void deleteOffresStage(long id);
     public OffresStages updateRating(Long id, int newRating);

     public OffresStages creatOffresStage(OffresStages offre, Set<Long> technologyIds);
     public OffresStages ajouterdemandes(OffresStages offresStages);
     public void archiverOffre(long id);
     public List<OffresStages> findByEtat(String etat);

}
