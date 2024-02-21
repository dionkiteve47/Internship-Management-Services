package com.example.intershipmanagement.services.Imlp;

import com.example.intershipmanagement.entities.Support;
import com.example.intershipmanagement.repositories.SupportRepository;
import com.example.intershipmanagement.services.Interface.SupportInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupportService implements SupportInterface {

    SupportRepository supportRepository;
    @Override
    public Support addSupport(Support s) {
        return supportRepository.save(s);
    }

    @Override
    public void updateSupport(Support s) {
        supportRepository.save(s);
    }

    @Override
    public List<Support> retriveAllSupport() {
        return supportRepository.findAll();
    }

    @Override
    public Support retriveSupportByID(Long id) {
        return supportRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSupport(Long id) {
        supportRepository.deleteById(id);
    }
}
