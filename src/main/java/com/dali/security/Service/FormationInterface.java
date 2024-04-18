package com.dali.security.Service;

import com.dali.security.Entity.Formation;

import java.util.List;

public interface FormationInterface {

    Formation addFormation(Formation f);
    Formation updateFormation(Formation f);

    List<Formation> retrieveAllFormation();

    Formation retrieveFormationByID(Long id);

    void deleteFormation(Long id);

    //*******************AVANCEES*****************



}
