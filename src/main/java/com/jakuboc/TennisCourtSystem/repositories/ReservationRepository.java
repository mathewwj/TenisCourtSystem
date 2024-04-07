package com.jakuboc.TennisCourtSystem.repositories;

import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;

import java.util.List;

/**
 * extension of {@link GenericCrudRepository} for {@link Reservation} specific queries
 */
public interface ReservationRepository extends GenericCrudRepository<Reservation, Long>{
    /**
     * gets all Reservations with {@code startTime} in the future,
     * reserved by User {@code phoneNumber} and sorted by {@code createTime}
     */
    List<Reservation> findAllPhoneNumber(String phoneNumber);

}
