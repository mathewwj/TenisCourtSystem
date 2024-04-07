package com.jakuboc.TennisCourtSystem.repositories.Impl;

import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;
import com.jakuboc.TennisCourtSystem.repositories.ReservationRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepositoryImpl extends GenericCrudRepositoryImpl<Reservation, Long> implements ReservationRepository {
    @Autowired
    protected ReservationRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Reservation.class);
    }

    @Override
    public List<Reservation> findAllPhoneNumber(String phoneNumber) {
        String hql = "SELECT r FROM Reservation r " +
                "WHERE r.user.phoneNumber = :phoneNumber " +
                "AND r.startTime > CURRENT_TIMESTAMP " +
                "ORDER BY r.createdTime";

        return super.getSession()
                .createQuery(hql, Reservation.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
    }
}
