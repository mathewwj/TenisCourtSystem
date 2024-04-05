package com.jakuboc.TennisCourtSystem.Services;

import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
    List<Reservation> findAllSorted();
    List<Reservation> findAllPhoneNumber(String phoneNumber);
}
