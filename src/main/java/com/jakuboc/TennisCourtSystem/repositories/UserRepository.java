package com.jakuboc.TennisCourtSystem.repositories;

import com.jakuboc.TennisCourtSystem.domain.entities.User;

/**
 * extension of {@link GenericCrudRepository} for {@link User} specific queries
 */
public interface UserRepository extends GenericCrudRepository<User, String>{
}
