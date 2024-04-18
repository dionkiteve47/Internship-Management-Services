package com.dali.security.Service;

import com.dali.security.Entity.Reservation;

import java.util.List;
import java.util.Map;

public interface IReservService {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getAllReservation();
    Reservation getReservationById(long idReserv);
    void deleteReservation(long idReserv);
    Reservation updateReservation(Reservation reservation);
    Reservation AddReservationAndAssign(Reservation reservation,long IdEvent);
    public String reserver(Long IdEvent, Reservation reservation);
    /*public List<Object[]> getMonthlyReservationCountsByYear(int year);*/
    public Map<String, Integer> statReservationParEvenement();



}
