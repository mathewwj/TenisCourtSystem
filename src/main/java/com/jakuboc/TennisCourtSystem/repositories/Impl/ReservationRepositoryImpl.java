package com.jakuboc.TennisCourtSystem.repositories.Impl;

import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;
import com.jakuboc.TennisCourtSystem.repositories.ReservationRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepositoryImpl extends GenericCrudRepositoryImpl<Reservation, Long> implements ReservationRepository {
    @Autowired
    protected ReservationRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Reservation.class);
    }
}
