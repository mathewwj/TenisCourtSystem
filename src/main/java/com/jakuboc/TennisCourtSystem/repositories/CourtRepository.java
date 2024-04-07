package com.jakuboc.TennisCourtSystem.repositories;

import com.jakuboc.TennisCourtSystem.domain.entities.Court;

/**
 * extension of {@link GenericCrudRepository} for {@link Court} specific queries
 */
public interface CourtRepository extends GenericCrudRepository<Court, Long> {

}
