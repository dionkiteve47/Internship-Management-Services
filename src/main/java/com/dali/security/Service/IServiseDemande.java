package com.dali.security.Service;

import com.dali.security.Entity.Demandes;
import com.dali.security.Entity.OffresStages;
import com.dali.security.Entity.Etatdemande;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

public interface IServiseDemande {

    public Demandes ajouterdemandes (Demandes demandes);
    public Demandes updatedemandes (Demandes demandes);
    public Demandes getdemandesById(long id);
    public List<Demandes> getAlldemandes();
    public void deletedemandes(long id);
    //public List<Demandes>getDemandesByidoff(Long idoff);
    public void refuserDemande(long id);
    public void acceptDemande(long id);
    public List<Demandes> findByEtat(String etat);
    public void sendEmailNotification(Demandes demande);
    public void EmailService(JavaMailSender javaMailSender);

}
