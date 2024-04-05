package com.jakuboc.TennisCourtSystem.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericCrudRepository<T, ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    Optional<T> save(ID id, T entity);
    Optional<T> update(ID id, T entity);
    void deleteById(ID id);
    boolean isExists(ID id);

}
