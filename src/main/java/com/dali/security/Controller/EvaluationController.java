package com.dali.security.Controller;

import com.dali.security.Entity.Evaluation;
import com.dali.security.Service.ServiceEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private ServiceEvaluation serviceEvaluation;

    @PostMapping("/save")
    public ResponseEntity<Evaluation> saveEvaluation(@RequestBody Evaluation evaluation) {
        try {
            Evaluation savedEvaluation = serviceEvaluation.saveEvaluation(evaluation);
            return ResponseEntity.ok(savedEvaluation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
