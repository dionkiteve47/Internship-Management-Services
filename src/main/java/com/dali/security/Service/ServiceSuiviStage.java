package com.dali.security.Service;

import com.dali.security.Entity.Stage;
import com.dali.security.Entity.SuiviStage;
import com.dali.security.Entity.ValidationStatus;
import com.dali.security.Repository.StageRepository;
import com.dali.security.Repository.SuiviStageRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.PathResource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceSuiviStage implements IServiceSuiviStage {

    @Autowired
    private SuiviStageRepository suiviStageRepository;




    @Override
    public void uploadStepDocument(Long suiviStageId, String stepName, MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        SuiviStage suiviStage = suiviStageRepository.findById(suiviStageId)
                .orElseThrow(() -> new IllegalArgumentException("SuiviStage not found with id: " + suiviStageId));

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = "C:\\Users\\trabe\\Desktop\\Myfiles\\" + fileName;
        // Define the path where you want to save the file

        // Save the file path to the appropriate step field in the SuiviStage entity
        switch (stepName) {
            case "step1":
                suiviStage.setStep1DocumentPath(filePath);
                break;
            case "step2":
                suiviStage.setStep2DocumentPath(filePath);
                break;
            case "step3":
                suiviStage.setStep3DocumentPath(filePath);
                break;
            case "step4":
                suiviStage.setStep4DocumentPath(filePath);
                break;
            default:
                throw new IllegalArgumentException("Invalid step name: " + stepName);
        }

        // Save the updated entity
        suiviStageRepository.save(suiviStage);

        // Save the file to the specified path
        // file.transferTo(new File(filePath));

    }

    @Override
    public void uploadFirstDocument(String stepName, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Create a new SuiviStage entity
        SuiviStage suiviStage = new SuiviStage();

        // Define the path where you want to save the file
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = "C:\\Users\\trabe\\Desktop\\Myfiles\\" + fileName;

        // Set the appropriate step document path based on the stepName
        switch (stepName) {
            case "step1":
                suiviStage.setStep1DocumentPath(filePath);
                break;
            case "step2":
                suiviStage.setStep2DocumentPath(filePath);
                break;
            case "step3":
                suiviStage.setStep3DocumentPath(filePath);
                break;
            case "step4":
                suiviStage.setStep4DocumentPath(filePath);
                break;
            default:
                throw new IllegalArgumentException("Invalid step name: " + stepName);
        }

        // Save the new SuiviStage entity
        suiviStageRepository.save(suiviStage);

        // Save the file to the specified path
        // file.transferTo(new File(filePath));
    }

    @Override
    public void uploadFirstStep(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Create a new SuiviStage entity
        SuiviStage suiviStage = new SuiviStage();

        // Define the path where you want to save the file
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = "C:\\Users\\trabe\\Desktop\\Myfiles\\" + fileName;

        // Set the document path for the first step
        suiviStage.setStep1DocumentPath(filePath);
        suiviStage.setStep1ValidationStatus(null); // Or set it to any default value as needed

        // Save the new SuiviStage entity
        suiviStageRepository.save(suiviStage);

        // Save the file to the specified path
        // file.transferTo(new File(filePath));
    }

    @Override
    public SuiviStage updateStep1ValidationStatus(Long id) {
        Optional<SuiviStage> optionalSuiviStage = suiviStageRepository.findById(id);
        if (optionalSuiviStage.isPresent()) {
            SuiviStage suiviStage = optionalSuiviStage.get();
            suiviStage.setStep1ValidationStatus(ValidationStatus.APPROVED); // Set status to APPROVED
            return suiviStageRepository.save(suiviStage);
        } else {
            throw new EntityNotFoundException("SuiviStage with id " + id + " not found.");
        }
    }

    @Override
    public SuiviStage updateStep1ValidationStatusToRejected(Long id) {
        Optional<SuiviStage> optionalSuiviStage = suiviStageRepository.findById(id);
        if (optionalSuiviStage.isPresent()) {
            SuiviStage suiviStage = optionalSuiviStage.get();
            suiviStage.setStep1ValidationStatus(ValidationStatus.REJECTED); // Set status to REJECTED
            return suiviStageRepository.save(suiviStage);
        } else {
            throw new EntityNotFoundException("SuiviStage with id " + id + " not found.");
        }
    }

    // Implement other service methods if needed


    @Override
    public SuiviStage updateStep2ValidationStatusToApproved(Long id) {
        Optional<SuiviStage> optionalSuiviStage = suiviStageRepository.findById(id);
        if (optionalSuiviStage.isPresent()) {
            SuiviStage suiviStage = optionalSuiviStage.get();
            suiviStage.setStep2ValidationStatus(ValidationStatus.APPROVED); // Set status to APPROVED
            return suiviStageRepository.save(suiviStage);
        } else {
            throw new EntityNotFoundException("SuiviStage with id " + id + " not found.");
        }
    }

    @Override
    public SuiviStage updateStep2ValidationStatusToRejected(Long id) {
        Optional<SuiviStage> optionalSuiviStage = suiviStageRepository.findById(id);
        if (optionalSuiviStage.isPresent()) {
            SuiviStage suiviStage = optionalSuiviStage.get();
            suiviStage.setStep2ValidationStatus(ValidationStatus.REJECTED); // Set status to REJECTED
            return suiviStageRepository.save(suiviStage);
        } else {
            throw new EntityNotFoundException("SuiviStage with id " + id + " not found.");
        }
    }

    // Implement other service methods if needed

    @Override
    public SuiviStage updateStep3ValidationStatusToApproved(Long id) {
        Optional<SuiviStage> optionalSuiviStage = suiviStageRepository.findById(id);
        if (optionalSuiviStage.isPresent()) {
            SuiviStage suiviStage = optionalSuiviStage.get();
            suiviStage.setStep3ValidationStatus(ValidationStatus.APPROVED); // Set status to APPROVED
            return suiviStageRepository.save(suiviStage);
        } else {
            throw new EntityNotFoundException("SuiviStage with id " + id + " not found.");
        }
    }

    @Override
    public SuiviStage updateStep3ValidationStatusToRejected(Long id) {
        Optional<SuiviStage> optionalSuiviStage = suiviStageRepository.findById(id);
        if (optionalSuiviStage.isPresent()) {
            SuiviStage suiviStage = optionalSuiviStage.get();
            suiviStage.setStep3ValidationStatus(ValidationStatus.REJECTED); // Set status to REJECTED
            return suiviStageRepository.save(suiviStage);
        } else {
            throw new EntityNotFoundException("SuiviStage with id " + id + " not found.");
        }
    }


    @Override
    public void uploadSecondStep(MultipartFile file, Long suiviStageId) throws IOException {
        SuiviStage suiviStage = suiviStageRepository.findById(suiviStageId)
                .orElseThrow(() -> new EntityNotFoundException("SuiviStage with id " + suiviStageId + " not found."));

        // Check if the step1ValidationStatus is APPROVED
        if (suiviStage.getStep1ValidationStatus() == ValidationStatus.APPROVED) {
            // Update the step2DocumentPath with the uploaded file
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uploadDir = "C:\\Users\\trabe\\Desktop\\Myfiles\\";
            String filePath = uploadDir + "/" + fileName;
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            // Update the SuiviStage entity with the file path for step2DocumentPath
            suiviStage.setStep2DocumentPath(filePath);
            suiviStageRepository.save(suiviStage);
        } else {
            throw new IllegalStateException("Cannot upload file for the second step. First step validation is not approved.");
        }
    }

    @Override
    public void uploadThirdStep(MultipartFile file, Long suiviStageId) throws IOException {
        SuiviStage suiviStage = suiviStageRepository.findById(suiviStageId)
                .orElseThrow(() -> new EntityNotFoundException("SuiviStage with id " + suiviStageId + " not found."));

        // Check if the step1ValidationStatus is APPROVED
        if (suiviStage.getStep2ValidationStatus() == ValidationStatus.APPROVED) {
            // Update the step2DocumentPath with the uploaded file
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uploadDir = "C:\\Users\\trabe\\Desktop\\Myfiles\\";
            String filePath = uploadDir + "/" + fileName;
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            // Update the SuiviStage entity with the file path for step2DocumentPath
            suiviStage.setStep3DocumentPath(filePath);
            suiviStageRepository.save(suiviStage);
        } else {
            throw new IllegalStateException("Cannot upload file for the second step. First step validation is not approved.");
        }
    }

    @Override
    public void uploadLastStep(MultipartFile file, Long suiviStageId) throws IOException {
        SuiviStage suiviStage = suiviStageRepository.findById(suiviStageId)
                .orElseThrow(() -> new EntityNotFoundException("SuiviStage with id " + suiviStageId + " not found."));

        // Check if the step1ValidationStatus is APPROVED
        if (suiviStage.getStep3ValidationStatus() == ValidationStatus.APPROVED) {
            // Update the step2DocumentPath with the uploaded file
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uploadDir = "C:\\Users\\trabe\\Desktop\\Myfiles\\";
            String filePath = uploadDir + "/" + fileName;
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            // Update the SuiviStage entity with the file path for step2DocumentPath
            suiviStage.setStep4DocumentPath(filePath);
            suiviStageRepository.save(suiviStage);
        } else {
            throw new IllegalStateException("Cannot upload file for the second step. First step validation is not approved.");
        }
    }

    @Override
    public Resource downloadFile(String fileName) throws IOException {
        return null;
    }

    @Override
    public Resource downloadStep1Document(Long suiviStageId) throws IOException {
        return null ;
    }

    @Override
    public List<SuiviStage> getAllSuivis() {
        return suiviStageRepository.findAll();
    }

    @Override
    public void addFirstStep(MultipartFile file, Stage stage) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Create a new SuiviStage entity
        SuiviStage suiviStage = new SuiviStage();

        // Define the path where you want to save the file
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String directoryPath = "C:\\Users\\trabe\\Downloads\\Angular2-main\\Angular2-main\\src\\assets\\files";
        String filePath = directoryPath + File.separator + fileName;

        Path uploadPath = Paths.get(directoryPath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);


        // Set the document path for the first step
        suiviStage.setStep1DocumentPath(filePath);
        suiviStage.setStep1ValidationStatus(null); // Or set it to any default value as needed

        // Set the related Stage entity
        suiviStage.setStage(stage);

        // Save the new SuiviStage entity
        suiviStageRepository.save(suiviStage);

        // Save the file to the specified path
        // file.transferTo(new File(filePath));
    }

    @Override
    public SuiviStage getSuiviStageById(Long id) {
        return suiviStageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SuiviStage not found with id: " + id));

    }

    @Override
    public void uploadFileForSuiviStage(Long stageId, MultipartFile file) throws IOException {

    }


}
