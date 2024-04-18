package com.dali.security.Controller;

import com.dali.security.Entity.Stage;
import com.dali.security.Service.IServiceStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/Stage")
public class StageController {

    @Autowired
    private IServiceStage serviceStage;

    @PostMapping("/add")
    public Stage addStage(@RequestBody Stage C) {

        return serviceStage.addStage(C);
    }

    @GetMapping("/all")
    public List<Stage> getAllStages() {

        return serviceStage.getAllStages();
    }

    @PutMapping("/update")
    public Stage updateStage(@RequestBody Stage C){

        return serviceStage.updateStage(C); }


}
