package com.jakuboc.TennisCourtSystem.repositories.Impl;

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
        return getSession().createQuery( "select e from " + clazz.getSimpleName() + " e", clazz).list();
    }

    @Override
    @Transactional
    public Optional<T> save(ID id, T entity) {
        if (isExists(id)) {
            return Optional.empty();
        }

        getSession().persist(entity);
        getSession().flush();

        return findById(id);
    }

    @Override
    @Transactional
    public Optional<T> update(ID id, T entity) {
        if (!isExists(id)) {
            return Optional.empty();
        }

        getSession().merge(entity);
        return findById(id);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        findById(id).ifPresent(e -> getSession().remove(e));
    }

    @Override
    @Transactional
    public boolean isExists(ID id) {
        return findById(id).isPresent();
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
