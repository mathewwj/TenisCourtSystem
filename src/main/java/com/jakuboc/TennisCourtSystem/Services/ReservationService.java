package com.jakuboc.TennisCourtSystem.Services;

import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
    List<Reservation> findAllSorted();
    List<Reservation> findAllPhoneNumber(String phoneNumber);
    Optional<Reservation> create(Reservation reservation);
    void deleteById(Long id);
    Optional<Reservation> partialUpdate(Long id, Reservation reservation);
}
