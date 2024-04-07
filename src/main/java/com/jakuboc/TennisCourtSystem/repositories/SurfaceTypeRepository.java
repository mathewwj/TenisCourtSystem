package com.jakuboc.TennisCourtSystem.repositories;

import com.jakuboc.TennisCourtSystem.domain.entities.SurfaceType;

/**
 * extension of {@link GenericCrudRepository} for {@link SurfaceType} specific queries
 */
public interface SurfaceTypeRepository extends GenericCrudRepository<SurfaceType, Long>{
}
