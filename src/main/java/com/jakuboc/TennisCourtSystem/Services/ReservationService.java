package com.jakuboc.TennisCourtSystem.Services;

import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
    /**
     * gets all Reservations sorted by {@code createTime}
     */
    List<Reservation> findAllSorted();

    /**
     * gets all Reservations with {@code startTime} in the future,
     * reserved by User {@code phoneNumber} and sorted by {@code createTime}
     */
    List<Reservation> findAllPhoneNumber(String phoneNumber);
    Optional<Reservation> create(Reservation reservation);
    void deleteById(Long id);
    Optional<Reservation> partialUpdate(Long id, Reservation reservation);
}
