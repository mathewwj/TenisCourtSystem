package com.jakuboc.TennisCourtSystem.Services.Impl;

import com.jakuboc.TennisCourtSystem.Services.CourtService;
import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import com.jakuboc.TennisCourtSystem.repositories.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourtServiceImpl implements CourtService {
    private final CourtRepository courtRepository;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public Optional<Court> save(Court court) {
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
}
