package com.dali.security.Service;

import com.dali.security.Entity.OffresStages;
import com.dali.security.Entity.Technologies;

import java.util.List;

public interface ITechS {
    Technologies ajouterITechS(Technologies tech);
    Technologies updateITechS(Technologies tech);
    Technologies getITechSById(long id);
    List<Technologies> getAllITechS();
    void deleteITechS(long id);
}
