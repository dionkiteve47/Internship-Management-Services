package com.dali.security.Service;

import com.dali.security.Entity.Demandes;
import com.dali.security.Entity.Etatdemande;
import com.dali.security.Repository.DemandesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;


@AllArgsConstructor
@Slf4j
@Service
public class DemandeServise implements IServiseDemande{
    DemandesRepository demandesRepository;

   private  JavaMailSender javaMailSender;

    @Autowired
    public void EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Demandes ajouterdemandes(Demandes demandes) {
        demandes.setEtat("En Court");
        return demandesRepository.save(demandes);
    }

    @Override
    public List<Demandes> getAlldemandes() {
        return (List<Demandes>) demandesRepository.findAll();
    }

    @Override
    public Demandes getdemandesById(long id) {
        return demandesRepository.findById(id).get();
    }

    @Override
    public void deletedemandes(long id) {
        demandesRepository.deleteById(id);
    }


    @Transactional
    public void acceptDemande(long id) {
        Demandes demande = demandesRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        demande.setEtat("ACCEPATER");
        demandesRepository.save(demande);
    }
    @Transactional
    public void refuserDemande(long id) {
        Demandes demande = demandesRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        demande.setEtat("REFUSER");
        demandesRepository.save(demande);
    }
    @Override
    public void sendEmailNotification(Demandes demande) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("dahmaninajwa20@gmail.com");
        message.setSubject("demand de stage");
        message.setText("Form Data:\n" + demande.toString());
       javaMailSender.send(message);
    }

@Override
    public List<Demandes> findByEtat(String etat) {
        return demandesRepository.findByEtat(etat);
    }


    @Override
    public Demandes updatedemandes(Demandes demandes) {
        return demandesRepository.save(demandes);
    }


}