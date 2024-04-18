package com.dali.security.Service;

import com.dali.security.Entity.Stage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceStage {


    public List<Stage> getAllStages();
    Stage addStage(Stage stage);

    public Stage updateStage(Stage stage);
    public Stage getStageById(Long id);

}
