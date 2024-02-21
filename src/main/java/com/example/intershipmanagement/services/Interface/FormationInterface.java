package com.example.intershipmanagement.services.Interface;

import com.example.intershipmanagement.entities.Formation;
import com.example.intershipmanagement.entities.Video;

import java.util.List;

public interface FormationInterface {

    Formation addFormation(Formation f);
    Formation updateFormation(Formation f);

    List<Formation> retriveAllFormation();

    Formation retriveFormationByID(Long id);

    void deleteFormation(Long id);
}
