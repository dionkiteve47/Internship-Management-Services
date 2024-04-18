package com.dali.security.Repository;

import com.dali.security.Entity.Demandes;
import com.dali.security.Entity.OffresStages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffreRepository extends CrudRepository<OffresStages,Long> {
    List<OffresStages> findByEtat (String etat);



}
