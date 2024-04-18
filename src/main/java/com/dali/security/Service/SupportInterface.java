package com.dali.security.Service;

import com.dali.security.Entity.Support;

import java.util.List;

public interface SupportInterface {

    Support addSupport(Support s);
    Support updateSupport(Support s);

    List<Support> retrieveAllSupport();

    Support retrieveSupportByID(Long id);

    void deleteSupport(Long id);



    Support AddSupportAndAssign(Support support, long id);


}
