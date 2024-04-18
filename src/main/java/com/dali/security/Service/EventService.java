package com.dali.security.Service;

import com.dali.security.Entity.EvenementStatistics;
import com.dali.security.Entity.Event;
import com.dali.security.Repository.IEventRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventService implements IEventService {
    IEventRepository iEventRepository;
    //private final Path fileStorageLocation;
    @Override
    public Event addEvent(Event event) {
        return iEventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvent() {
        return iEventRepository.findAll();
    }

    @Override
    public Event getEventById(long idEvent) {
        return iEventRepository.findById(idEvent).get();
    }

    @Override
    public void deleteEvent(long idEvent) {
        iEventRepository.deleteById(idEvent);

    }

    @Override
    public Event updateEvent(Event event) {
        return iEventRepository.save(event);
    }

    public Event findById(Long id) {
        return iEventRepository.findById(id).orElseThrow(() -> new RuntimeException("Événement non trouvé"));
    }


    public List<EvenementStatistics> getEvenementStatistics() {
        List<Event> events = iEventRepository.findAll();

        return events.stream()
                .map(dataObject -> new EvenementStatistics(dataObject.getNom(), dataObject.getReservations().size()))
                .collect(Collectors.toList());
    }
    /*public Event handleImageFileUpload(MultipartFile fileImage, long id) {
        if (fileImage.isEmpty()) {
            return null;
        }
        String fileName = fileStorageService.storeFile(fileImage);
        Event club = courseService.findById(id).orElse(null);
        club.setImgUrl(fileName);
        return iEventRepository.save(club);
    }*/
   /* @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        // Essayez de créer le répertoire ici
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }*/
}



