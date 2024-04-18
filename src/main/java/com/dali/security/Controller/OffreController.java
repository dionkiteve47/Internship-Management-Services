package com.dali.security.Controller;

import com.dali.security.Service.IServiceOffreS;
import com.dali.security.Service.ITechS;
import com.dali.security.Entity.OffresStages;
import com.dali.security.Entity.Technologies;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/controller")
@CrossOrigin(origins = "http://localhost:4200")
public class OffreController {
    @Autowired
    IServiceOffreS serviceOffreS;
    @Qualifier("renamedserviseTech")
    @Autowired
    ITechS technologieService;


    // Endpoint for adding an internship offer
    //@PostMapping("/addOffresStage")
    //public ResponseEntity<OffresStages> addOffresStage(@RequestBody OffresStages offresStages) {
        //try {
            // Call the service method to add the internship offer
           // OffresStages newOffresStages = serviceOffreS.creatOffresStage(offresStages);
            // Return the newly created internship offer along with HTTP status 201 (Created)
           // return new ResponseEntity<>(newOffresStages, HttpStatus.CREATED);
        //} catch (Exception e) {
            // If an exception occurs, return HTTP status 500 (Internal Server Error)
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       // }
   // }

    @PostMapping("/addOffresStage")
    public ResponseEntity<OffresStages> addOffre(@RequestBody OffresStages offre, @RequestParam Set<Long> technologyIds) {
        OffresStages newOffre = serviceOffreS.creatOffresStage(offre, technologyIds);
        return ResponseEntity.ok().body(newOffre);
    }

    @GetMapping("/allTechnologies")
    public List<Technologies> getAllTechnologies() {
        return technologieService.getAllITechS();
    }
    @PostMapping("addtech")
    public Technologies addtech (@RequestBody Technologies tech){
        return technologieService.ajouterITechS(tech);
    }

    @GetMapping("getOffre")
    public Iterable<OffresStages> getAllOffre(){
        return serviceOffreS.getAllOffresStage();
    }
    @PostMapping("addOffre")
    public OffresStages addOffre (@RequestBody OffresStages offresStages){
        return serviceOffreS.ajouterOffresStage(offresStages);
    }
    @PutMapping("/rating/{id}")
    public ResponseEntity<OffresStages> updateRating(@PathVariable Long id, @RequestBody OffresStages updatedoffre) {
        OffresStages evaluation = serviceOffreS.updateRating(id, updatedoffre.getStarRating());

        if (evaluation != null) {
            return new ResponseEntity<>(evaluation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("getOffreById/{id}")
    public OffresStages getAllOffreByID(@PathVariable long id){
        return serviceOffreS.getOffresStageById(id);
    }
    @DeleteMapping("deleteOffre/{id}")
    public void deleteOffre(@PathVariable long id){
        serviceOffreS.deleteOffresStage(id);
    }
    @PutMapping("updateOffre")
    public OffresStages updateOffre(@RequestBody OffresStages b){
        return serviceOffreS.updateOffresStage(b);
    }

    @PutMapping("/{id}/arrchiver")
    public ResponseEntity<String> arrchiveroffre(@PathVariable long id) {
        try {
            serviceOffreS.archiverOffre(id);
            return ResponseEntity.ok("offre archiver successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("offre not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error arrchiver offre");
        }
    }
    @GetMapping("/filterByEtat/{etat}")
    public List<OffresStages> filterByEtat(@PathVariable String etat) {
        System.out.println("Received etat: " + etat);
        return serviceOffreS.findByEtat(etat);
    }
    //private final Map<String, Map<String, String>> translations = new HashMap<>();

    //@PostConstruct
    //public void init() {
        // Populate translations (replace with your actual translation data)
        //Map<String, String> englishTranslations = new HashMap<>();
       // englishTranslations.put("helloWorld", "Hello, World!");
        //englishTranslations.put("greeting", "Welcome to our website!");

        //Map<String, String> frenchTranslations = new HashMap<>();
        //frenchTranslations.put("helloWorld", "Bonjour, le monde!");
        //frenchTranslations.put("greeting", "Bienvenue sur notre site web!");

        //translations.put("en", englishTranslations);
       // translations.put("fr", frenchTranslations);
   // }

  //  @GetMapping("/translations")
    //public Map<String, String> getTranslations(@RequestParam("lang") String lang) {
       // return translations.getOrDefault(lang, new HashMap<>());
    //}
}



