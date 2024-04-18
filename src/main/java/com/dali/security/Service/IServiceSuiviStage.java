package com.dali.security.Service;

import com.dali.security.Entity.SuiviStage;
import com.dali.security.Entity.Stage;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceSuiviStage {
    public void uploadStepDocument(Long suiviStageId, String stepName, MultipartFile file) throws IOException ;
    public void uploadFirstDocument(String stepName, MultipartFile file) throws IOException;

    void uploadFirstStep(MultipartFile file) throws IOException;

    public SuiviStage updateStep1ValidationStatus(Long id);
    SuiviStage updateStep1ValidationStatusToRejected(Long id);

    SuiviStage updateStep2ValidationStatusToApproved(Long id);
    SuiviStage updateStep2ValidationStatusToRejected(Long id);

    SuiviStage updateStep3ValidationStatusToApproved(Long id);
    SuiviStage updateStep3ValidationStatusToRejected(Long id);

    void uploadSecondStep(MultipartFile file, Long suiviStageId) throws IOException;
    // Add other service methods if needed
    public List<SuiviStage> getAllSuivis();

    void addFirstStep(MultipartFile file, Stage stage) throws IOException;

    public SuiviStage getSuiviStageById(Long id);

    public void uploadFileForSuiviStage(Long stageId, MultipartFile file) throws IOException;

    public void uploadThirdStep(MultipartFile file, Long suiviStageId) throws IOException;
    public void uploadLastStep(MultipartFile file, Long suiviStageId) throws IOException;

    Resource downloadFile(String fileName) throws IOException;
    public Resource downloadStep1Document(Long suiviStageId) throws IOException;
}
