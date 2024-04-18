package com.dali.security.Controller;

import com.dali.security.Entity.Stage;
import com.dali.security.Entity.SuiviStage;
import com.dali.security.Service.IServiceStage;
import  com.dali.security.Service.IServiceSuiviStage;
import  com.dali.security.Service.ServiceStage;
import  com.dali.security.Service.ServiceSuiviStage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/SuiviStage")
public class SuiviStageController {


    @Autowired
    private ServiceSuiviStage suiviStageService;
    @Autowired
    private ServiceStage serviceStage;

    @PostMapping("/{suiviStageId}/upload")
    public ResponseEntity<?> uploadStepDocument(
            @PathVariable Long suiviStageId,
            @RequestParam String stepName,
            @RequestParam MultipartFile file
    ) {
        try {
            suiviStageService.uploadStepDocument(suiviStageId, stepName, file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

    @PostMapping("/uploadFirstDocument")
    public ResponseEntity<String> uploadFirstDocument(
            @RequestParam String stepName,
            @RequestParam MultipartFile file
    ) {
        try {
            suiviStageService.uploadFirstDocument(stepName, file);
            return ResponseEntity.ok("File uploaded successfully for the first step.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }

    @PostMapping("/uploadFirstStep")
    public String uploadFirstStep(@RequestParam("file") MultipartFile file) {
        try {
            suiviStageService.uploadFirstStep(file);
            return "success";
        } catch (IOException e) {
            return "error";
        }
    }


    @PutMapping("/{id}/step1-approve")
    public ResponseEntity<?> approveStep1ValidationStatus(@PathVariable Long id) {
        try {
            SuiviStage updatedSuiviStage = suiviStageService.updateStep1ValidationStatus(id);
            return ResponseEntity.ok(updatedSuiviStage);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/step1-reject")
    public ResponseEntity<?> rejectStep1ValidationStatus(@PathVariable Long id) {
        try {
            SuiviStage updatedSuiviStage = suiviStageService.updateStep1ValidationStatusToRejected(id);
            return ResponseEntity.ok(updatedSuiviStage);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}/step2-approve")
    public ResponseEntity<?> approveStep2ValidationStatus(@PathVariable Long id) {
        try {
            SuiviStage updatedSuiviStage = suiviStageService.updateStep2ValidationStatusToApproved(id);
            return ResponseEntity.ok(updatedSuiviStage);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/step2-reject")
    public ResponseEntity<?> rejectStep2ValidationStatus(@PathVariable Long id) {
        try {
            SuiviStage updatedSuiviStage = suiviStageService.updateStep2ValidationStatusToRejected(id);
            return ResponseEntity.ok(updatedSuiviStage);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/step3-approve")
    public ResponseEntity<?> approveStep3ValidationStatus(@PathVariable Long id) {
        try {
            SuiviStage updatedSuiviStage = suiviStageService.updateStep3ValidationStatusToApproved(id);
            return ResponseEntity.ok(updatedSuiviStage);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/step3-reject")
    public ResponseEntity<?> rejectStep3ValidationStatus(@PathVariable Long id) {
        try {
            SuiviStage updatedSuiviStage = suiviStageService.updateStep3ValidationStatusToRejected(id);
            return ResponseEntity.ok(updatedSuiviStage);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/uploadSecondStep")
    public ResponseEntity<String> uploadSecondStep(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        try {
            suiviStageService.uploadSecondStep(file, id);
            return ResponseEntity.ok("File uploaded successfully for the second step.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @PutMapping("/{id}/uploadThirdStep")
    public ResponseEntity<String> uploadThirdtep(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        try {
            suiviStageService.uploadThirdStep(file, id);
            return ResponseEntity.ok("File uploaded successfully for the second step.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }




    }

    @PutMapping("/{id}/uploadLastStep")
    public ResponseEntity<String> uploadLaststep(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        try {
            suiviStageService.uploadLastStep(file, id);
            return ResponseEntity.ok("File uploaded successfully for the second step.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }




    }

    @GetMapping("/AllSuivis")
    public List<SuiviStage> getAllSuivisStage() {

        return suiviStageService.getAllSuivis();
    }

    @PostMapping("/first")
    public ResponseEntity<?> addFirstStep(@RequestParam("file") MultipartFile file, @RequestParam("stageId") Long stageId) {
        try {
            Stage stage = serviceStage.getStageById(stageId);
            suiviStageService.addFirstStep(file, stage);
            return ResponseEntity.ok("First step uploaded successfully for the specified Stage.");
        } catch (IOException e) {
            // Handle the IOException here (e.g., log the error, return an error response)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload first step: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuiviStage> getSuiviStageById(@PathVariable Long id) {
        SuiviStage suiviStage = suiviStageService.getSuiviStageById(id);
        return new ResponseEntity<>(suiviStage, HttpStatus.OK);
    }

}
