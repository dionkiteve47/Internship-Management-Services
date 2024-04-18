package com.example.internshipmanagement.services.Interface;

import com.example.internshipmanagement.entities.Formation;
import com.example.internshipmanagement.entities.Video;

import java.util.List;

public interface FormationInterface {

    Formation addFormation(Formation f);
    Formation updateFormation(Formation f);

    List<Formation> retrieveAllFormation();

    Formation retrieveFormationByID(Long id);

    void deleteFormation(Long id);

    //*******************AVANCEES*****************



}
