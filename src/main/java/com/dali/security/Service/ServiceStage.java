package com.dali.security.Service;

import com.dali.security.Entity.Stage;
import com.dali.security.Repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceStage implements IServiceStage {

    @Autowired
    private StageRepository stageRepository;
    @Override
    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }
    @Override
    public Stage addStage(Stage stage) {
        return stageRepository.save(stage);
    }

    @Override
    public Stage updateStage(Stage stage) {
        Long id = stage.getId();
        if (stageRepository.existsById(id)) {
            return stageRepository.save(stage);
        } else {

            return null;
        }
    }

    @Override
    public Stage getStageById(Long id) {
        Optional<Stage> stageOptional = stageRepository.findById(id);
        return stageOptional.orElse(null); // Return null if Stage is not found;
    }


}

