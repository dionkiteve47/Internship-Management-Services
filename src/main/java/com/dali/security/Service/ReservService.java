package com.dali.security.Service;

import com.dali.security.Entity.Event;
import com.dali.security.Entity.Reservation;
import com.dali.security.Repository.IEventRepository;
import com.dali.security.Repository.ReservRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservService implements IReservService{

    ReservRepository reservRepository;
    IEventRepository eventRepository;
    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservation() {
        return reservRepository.findAll();
    }

    @Override
    public Reservation getReservationById(long idReserv) {
        return reservRepository.findById(idReserv).get();
    }

    @Override
    public void deleteReservation(long idReserv) {
        reservRepository.deleteById(idReserv);

    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservRepository.save(reservation);
    }

    @Override
    public Reservation AddReservationAndAssign(Reservation reservation,long IdEvent) {
        Event event = eventRepository.findById(IdEvent).get();
        reservation.setEvent(event);
        return reservRepository.save(reservation);

    }

    @Override
    public String reserver(Long IdEvent, Reservation reservation) {
        Event event = eventRepository.findById(IdEvent).orElseThrow(() -> new RuntimeException("Événement non trouvé"));

        if (event.getNbrPlace() > 0) {
            event.setNbrPlace(event.getNbrPlace() - 1);
            eventRepository.save(event);
            // Enregistrer la réservation
            return "Réservation réussie";
        } else {
            return "L'événement est complet";
        }
    }

    /*@Override
    public List<Object[]> getMonthlyReservationCountsByYear(int year) {
        return  reservRepository.findMonthlyReservationCountsByYear(year);
    }*/
    @Override

        public Map<String, Integer> statReservationParEvenement() {
            Map<String, Integer> statResult = new HashMap<>();
            List<Event> evenements = eventRepository.findAll();

            for (Event evenement : evenements) {
                // Assuming 'reservations' is properly populated in the Evenement entity
                int reservationCount = evenement.getReservations() != null ? evenement.getReservations().size() : 0;
                statResult.put(evenement.getNom(), reservationCount);
            }

            return statResult;
        }}


