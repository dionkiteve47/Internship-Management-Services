package com.example.intershipmanagement.controller;

import com.example.intershipmanagement.entities.Formation;
import com.example.intershipmanagement.entities.Support;
import com.example.intershipmanagement.entities.Video;
import com.example.intershipmanagement.services.Imlp.FormationService;
import com.example.intershipmanagement.services.Imlp.SupportService;
import com.example.intershipmanagement.services.Imlp.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/gestionformation")
public class Gestionformation {
    FormationService formationService;
    SupportService supportService;
    VideoService videoService;

    //-----------------formation--------------

    @PostMapping("/addFormation")
    public Formation addFormation(@RequestBody Formation f) {
        return formationService.addFormation(f);
    }

    @PutMapping("/updateFormation")
    public Formation updateFormation(@RequestBody Formation f) {
        return formationService.updateFormation(f);
    }
    @GetMapping("/retriveFormationByID/{id}")
    public Formation retriveFormationByID( @PathVariable("id") Long id) {
        return formationService.retriveFormationByID(id);
    }
    @DeleteMapping("/deleteFormation/{id}")
    public void deleteFormation(@PathVariable("id")Long id) {
        formationService.deleteFormation(id);
    }
    @GetMapping("/retriveAllFormation")
    public List<Formation> retriveAllFormation() {
        return formationService.retriveAllFormation();
    }

    //-----------------Support--------------

    @PostMapping("/addSupport")
    public Support addSupport(@RequestBody Support s) {
        return supportService.addSupport(s);
    }

    @PutMapping("/updateSupport")
    public void updateSupport(@RequestBody Support s) {
        supportService.updateSupport(s);
    }
    @GetMapping("/retriveAllSuppport")
    public List<Support> retriveAllSuppport() {
        return supportService.retriveAllSupport();
    }
    @GetMapping("/retriveSupportByID/{id}")
    public Support retriveSupportByID(@PathVariable("id")Long id) {
        return supportService.retriveSupportByID(id);
    }
    @DeleteMapping("/deleteSupport/{id}")
    public void deleteSupport(@PathVariable("id") Long id) {
        supportService.deleteSupport(id);
    }


    //-----------------Video--------------


    @PostMapping("/addVideo")
    public Video addVideo(@RequestBody Video v) {
        return videoService.addVideo(v);
    }

    @PutMapping("/updateVideo")
    public void updateVideo(@RequestBody Video v) {
        videoService.updateVideo(v);
    }
    @GetMapping("/retriveAllVideo")
    public List<Video> retriveAllVideo() {
        return videoService.retriveAllVideo();
    }
    @GetMapping("/retriveVideoByID/{id}")
    public Video retriveVideoByID(@PathVariable("id") Long id) {
        return videoService.retriveVideoByID(id);
    }
    @DeleteMapping("/deleteVideo/{id}")
    public void deleteVideo(@PathVariable("id") Long id) {
        videoService.deleteVideo(id);
    }
}
