package com.example.internshipmanagement.services.Interface;

import com.example.internshipmanagement.entities.Support;

import java.util.List;

public interface SupportInterface {

    Support addSupport(Support s);
    Support updateSupport(Support s);

    List<Support> retrieveAllSupport();

    Support retrieveSupportByID(Long id);

    void deleteSupport(Long id);



    Support AddSupportAndAssign(Support support, long id);


}
