package com.jakuboc.TennisCourtSystem.repositories.Impl;

import com.jakuboc.TennisCourtSystem.domain.entities.User;
import com.jakuboc.TennisCourtSystem.repositories.GenericCrudRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;


public abstract class GenericCrudRepositoryImpl<T, ID> implements GenericCrudRepository<T, ID> {
    private final SessionFactory sessionFactory;
    private final Class<T> clazz;


    protected GenericCrudRepositoryImpl(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    @Override
    @Transactional
    public Optional<T> findById(ID id) {
        T entity = getSession().get(clazz, id);
        return Optional.ofNullable(entity);
    }

    @Override
    @Transactional
    public List<T> findAll() {
        // TODO change later
        return (List<T>) getSession().createQuery( "select e from User e", User.class).list();
    }

    @Override
    @Transactional
    public Optional<T> save(ID id, T entity) {
        getSession().persist(entity);
        getSession().flush();

        return findById(id);
    }

    @Override
    @Transactional
    public Optional<T> update(ID id, T entity) {
        // TODO
        return null;
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        findById(id).ifPresent(e -> getSession().remove(e));
        // TODO flush or commit
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
