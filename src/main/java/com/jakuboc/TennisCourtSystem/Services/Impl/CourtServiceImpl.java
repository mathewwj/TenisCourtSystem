package com.jakuboc.TennisCourtSystem.Services.Impl;

import com.jakuboc.TennisCourtSystem.Services.CourtService;
import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import com.jakuboc.TennisCourtSystem.domain.entities.SurfaceType;
import com.jakuboc.TennisCourtSystem.repositories.CourtRepository;
import com.jakuboc.TennisCourtSystem.repositories.SurfaceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourtServiceImpl implements CourtService {
    private final CourtRepository courtRepository;
    private final SurfaceTypeRepository surfaceTypeRepository;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepository, SurfaceTypeRepository surfaceTypeRepository) {
        this.courtRepository = courtRepository;
        this.surfaceTypeRepository = surfaceTypeRepository;
    }

    @Override
    public Optional<Court> save(Court court) {
        SurfaceType courtSurfaceType = court.getSurfaceType();
        Optional<SurfaceType> inMemorySurfaceType =
                surfaceTypeRepository.findById(courtSurfaceType.getId());
        if (inMemorySurfaceType.isEmpty() ||
                !Objects.equals(courtSurfaceType, inMemorySurfaceType.get())) {
            return Optional.empty();
        }

        return courtRepository.save(court.getId(), court);
    }

    @Override
    public List<Court> findAll() {
        return courtRepository.findAll();
    }

    @Override
    public Optional<Court> findById(Long id) {
        return courtRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        courtRepository.deleteById(id);
    }
}
