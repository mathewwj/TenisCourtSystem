package com.jakuboc.TennisCourtSystem.Services;

import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import com.jakuboc.TennisCourtSystem.repositories.Impl.CourtRepositoryImpl;

import java.util.List;
import java.util.Optional;

public interface CourtService {
     Optional<Court> save(Court court);
     List<Court> findAll();
     Optional<Court> findById(Long id);
     void deleteById(Long id);
     Optional<Court> partialUpdate(Long id, Court court);
}
