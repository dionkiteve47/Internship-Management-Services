package com.dali.security.Service;

import com.dali.security.Entity.Avis;

import java.util.List;

public interface IAvisService {
    public List<Avis> retrieveAllAvis();
    public Avis retrieveAvis (Long avis);
    public Avis addAvis (Avis a);
    public void removeAvis (Long avis);
    public Avis modifyAvis (Avis avis);
    Avis AvisAndAssign(Avis avis, long IdEvent);
    public Avis addAvisToPostAndUser(Avis avis, Long eventId);
}
