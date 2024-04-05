package com.jakuboc.TennisCourtSystem.repositories.Impl;

import com.jakuboc.TennisCourtSystem.domain.entities.SurfaceType;
import com.jakuboc.TennisCourtSystem.repositories.SurfaceTypeRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SurfaceTypeRepositoryImpl extends GenericCrudRepositoryImpl<SurfaceType, Long> implements SurfaceTypeRepository {
    @Autowired
    protected SurfaceTypeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, SurfaceType.class);
    }
}
