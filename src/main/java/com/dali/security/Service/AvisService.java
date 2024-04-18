package com.dali.security.Service;

import com.dali.security.Entity.Avis;
import com.dali.security.Entity.Event;
import com.dali.security.Repository.AvisRepo;
import com.dali.security.Repository.IEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvisService implements IAvisService{
    IEventRepository eventRepository;
    AvisRepo avisRepo;
    BadWordFilterService badWordFilterService;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<Avis> retrieveAllAvis() {
        return avisRepo.findAll();
    }

    @Override
    public Avis retrieveAvis(Long avisId) {
        return avisRepo.findById(avisId).get();
    }

    @Override
    public Avis addAvis(Avis a) {


        return avisRepo.save(a);
    }

    @Override
    public void removeAvis(Long avisId) {
        avisRepo.deleteById(avisId);
    }

    @Override
    public Avis modifyAvis(Avis avis) {
        return avisRepo.save(avis);
    }

    @Override
    public Avis AvisAndAssign(Avis avis, long IdEvent) {
        Event event = eventRepository.findById(IdEvent).get();
        avis.setEvent(event);
        return avisRepo.save(avis);
    }


    @Override
    public Avis addAvisToPostAndUser(Avis avis, Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);

        if (event != null) {
            if (badWordFilterService.containsBadWord(avis.getContenu())) {
                throw new IllegalArgumentException("Comment contain inappropriate content or contains a subject that should not be posted here. Please review your post before submitting.");
            }

            avis.setEvent(event);
            try {
                Avis savedAvis = avisRepo.save(avis);
                event.getAvis().add(savedAvis);


                // Send WebSocket notification to /topic/comments
                String destination = "/topic/comments";
//                messagingTemplate.convertAndSend(destination, "New comment added to Post " + postId);
//                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//                 Message message = Message.creator(
//                    new com.twilio.type.PhoneNumber("+21692193577"),
//                           new com.twilio.type.PhoneNumber("+14123123496"),
//                   "salut ,"+ comment.getUser().getName()+" a ajoute ce commentaire  : "+ comment.getDescription()+" sur cette publication "+ comment.getPost().getTitle())
//                .create();
                return savedAvis;
            } catch (Exception e) {
                // Handle the exception (log, throw custom exception, etc.)
                return null;
            }
        } else {
            return null;
        }
    }


}









