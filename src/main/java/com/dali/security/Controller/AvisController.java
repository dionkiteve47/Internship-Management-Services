package com.dali.security.Controller;


import com.dali.security.Entity.Avis;
import com.dali.security.Service.BadWordFilterService;
import com.dali.security.Service.IAvisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Avis")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AvisController {

    IAvisService avisService;
    BadWordFilterService badWordFilterService;


    @GetMapping("/retrieve-all-avis")
    public List<Avis> getAvis(){
        List<Avis> listAviss = avisService.retrieveAllAvis();
        return listAviss;
    }
    @GetMapping("/retrieve-avis/{idAvis}")
    public Avis retrieveAvis(@PathVariable("idAvis") Long avisId) {
        Avis avis = avisService.retrieveAvis(avisId);
        return avis;
    }


    @PostMapping("/add-avis")
    public Avis addAvis(@RequestBody Avis e) {
        Avis avis = avisService.addAvis(e);
        return avis;
    }

    @PostMapping("/assign/{idEvent}")
    public Avis AvisAndAssign(@RequestBody Avis avis, @PathVariable("idEvent") long idEvent) {
        return avisService.AvisAndAssign(avis, idEvent);
    }

    @DeleteMapping("/remove-avis/{avis-id}")
    public void removeChambre(@PathVariable("avis-id") Long avisId) {
        avisService.removeAvis(avisId);
    }

    @PutMapping("/modify-avis")
    public Avis modifyavis(@RequestBody Avis e) {
        Avis avis  = avisService.modifyAvis(e);
        return avis;

    }

    @GetMapping("/fetchId")
    public Long fetchIdPost() {
        // Here you would fetch the idPost from your database or any other source
        // For demonstration purposes, let's assume you have a static postId
        Long postId = 1L; // Example: postId is fetched from your database or any other source
        return postId;
    }

    @PostMapping("/addCommentToPostAndUser/{idPost}")
    public ResponseEntity<?> addAvisToPostAndUser(@RequestBody Avis a,
                                                     @PathVariable("idPost") Long idPost) {
        // Récupération du contenu du commentaire
        String avisContenu = a.getContenu();
        // Vérification si le commentaire contient un mot inapproprié
        if (badWordFilterService.containsBadWord(avisContenu)) {
            return ResponseEntity.badRequest().body("Avis contains a bad word: " + avisContenu);
        }
        // Ajout du commentaire au post et à l'utilisateur
        Avis savedAvis = avisService.addAvisToPostAndUser(a, idPost);
        // Vérification si le commentaire a été ajouté avec succès
        if (savedAvis != null) {
            return ResponseEntity.ok(savedAvis);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add comment.");
        }
    }


    @PostMapping("/checkBadWord") // Define the endpoint path
    public ResponseEntity<Boolean> checkBadWord(@RequestBody String inputString) {
        boolean containsBadWord = badWordFilterService.containsBadWord(inputString);
        return ResponseEntity.ok(containsBadWord);
    }


}