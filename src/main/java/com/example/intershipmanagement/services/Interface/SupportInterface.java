package com.example.intershipmanagement.services.Interface;

import com.example.intershipmanagement.entities.Support;
import com.example.intershipmanagement.entities.Video;

import java.util.List;

public interface SupportInterface {

    Support addSupport(Support s);
    void updateSupport(Support s);

    List<Support> retriveAllSupport();

    Support retriveSupportByID(Long id);

    void deleteSupport(Long id);
}
