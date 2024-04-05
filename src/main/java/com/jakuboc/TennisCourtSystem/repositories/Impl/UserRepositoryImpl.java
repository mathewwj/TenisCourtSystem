package com.jakuboc.TennisCourtSystem.repositories.Impl;

import com.jakuboc.TennisCourtSystem.domain.entities.User;
import com.jakuboc.TennisCourtSystem.repositories.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends GenericCrudRepositoryImpl<User, String> implements UserRepository {
    @Autowired
    protected UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }
}
