package com.dali.security.Controller;

import com.dali.security.Service.IServiseDemande;
import com.dali.security.Entity.Demandes;
import com.dali.security.Entity.OffresStages;
import com.dali.security.Entity.Etatdemande;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controllerDemande")
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeController {
     @Autowired
     IServiseDemande serviseDemande;

    @PostMapping("ajoutDemande")
    public ResponseEntity<Demandes> ajouterDemande(@RequestBody Demandes demandes) {
        Demandes savedDemande = serviseDemande.ajouterdemandes(demandes);
        serviseDemande.sendEmailNotification(savedDemande); // Send email notification
        return new ResponseEntity<>(savedDemande, HttpStatus.CREATED);
    }
    @GetMapping("getDemande")
    public Iterable<Demandes> getAllDemande(){
        return serviseDemande.getAlldemandes();
    }
    @PostMapping("addDemande")
    public Demandes addDemande (@RequestBody Demandes demandes){
        return serviseDemande.ajouterdemandes(demandes);
    }

    //@GetMapping("/offre/{offreId}")
    //public List<Demandes> getDemandesByOffreId(@PathVariable Long idoff) {
        //return serviseDemande.getDemandesByidoff(idoff);
    //}
    @GetMapping("getDemandeById/{id}")
    public Demandes getAllDemandeByID(@PathVariable long id){
        return serviseDemande.getdemandesById(id);
    }
    @DeleteMapping("deleteDemande/{id}")
    public void delete(@PathVariable long id){
        serviseDemande.deletedemandes(id);
    }
    @PutMapping("updateDemande")
    public Demandes updateDemande(@RequestBody Demandes b){
        return serviseDemande.updatedemandes(b);
    }

// DemandesController.java
@PutMapping("/{id}/accept")
public ResponseEntity<String> acceptEvaluation(@PathVariable long id) {
    try {
        serviseDemande.acceptDemande(id);
        return ResponseEntity.ok("demande accepted successfully");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("demande not found");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accepting demande");
    }
}
    @PutMapping("/{id}/refuser")
    public ResponseEntity<String> refuser(@PathVariable long id) {
        try {
            serviseDemande.refuserDemande(id);
            return ResponseEntity.ok("demande refuser successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("demande not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error refuser demande");
        }
    }
    //@GetMapping("/filterByEtat/{etat}")
   // public List<Demandes> filterByEtat(@PathVariable String etat) {
       // return serviseDemande.findByEtat(etat);
    //}



    @GetMapping("/filterByEtat/{etat}")
    public List<Demandes> filterByEtat(@PathVariable String etat) {
        System.out.println("Received etat: " + etat);
        return serviseDemande.findByEtat(etat);
    }


}

