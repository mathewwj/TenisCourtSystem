package com.jakuboc.TennisCourtSystem.repositories.Impl;

import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import com.jakuboc.TennisCourtSystem.repositories.CourtRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourtRepositoryImpl extends GenericCrudRepositoryImpl<Court, Long> implements CourtRepository {

    @Autowired
    public CourtRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Court.class);
    }

}
