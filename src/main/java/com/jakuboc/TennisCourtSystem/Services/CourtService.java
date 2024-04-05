package com.jakuboc.TennisCourtSystem.Services;

import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import com.jakuboc.TennisCourtSystem.repositories.Impl.CourtRepositoryImpl;

import java.util.Optional;

public interface CourtService {
    public Optional<Court> save(Court court);
}
