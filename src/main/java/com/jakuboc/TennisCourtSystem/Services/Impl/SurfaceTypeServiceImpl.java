package com.jakuboc.TennisCourtSystem.Services.Impl;

import com.jakuboc.TennisCourtSystem.Services.SurfaceTypeService;
import com.jakuboc.TennisCourtSystem.domain.entities.SurfaceType;
import com.jakuboc.TennisCourtSystem.repositories.SurfaceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurfaceTypeServiceImpl implements SurfaceTypeService {
    private final SurfaceTypeRepository surfaceTypeRepository;

    public SurfaceTypeServiceImpl(SurfaceTypeRepository surfaceTypeRepository) {
        this.surfaceTypeRepository = surfaceTypeRepository;
    }

    @Override
    public List<SurfaceType> findAll() {
        return surfaceTypeRepository.findAll();
    }
}
