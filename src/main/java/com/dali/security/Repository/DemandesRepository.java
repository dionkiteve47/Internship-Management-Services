package com.dali.security.Repository;

import com.dali.security.Entity.Demandes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Repository
public interface DemandesRepository extends CrudRepository<Demandes,Long> {
//List<Demandes>findByidoff(Long idoff);
    List<Demandes> findByEtat(String etat);
}
