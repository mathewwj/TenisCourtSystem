package com.jakuboc.TennisCourtSystem.Services;

import com.jakuboc.TennisCourtSystem.domain.entities.SurfaceType;

import java.util.List;

public interface SurfaceTypeService {
    List<SurfaceType> findAll();
}
