package com.jakuboc.TennisCourtSystem.repositories;

import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;

import java.util.List;

public interface ReservationRepository extends GenericCrudRepository<Reservation, Long>{
    List<Reservation> findAllPhoneNumber(String phoneNumber);

}
