package com.jakuboc.TennisCourtSystem.Services.Impl;

import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import com.jakuboc.TennisCourtSystem.domain.entities.SurfaceType;
import com.jakuboc.TennisCourtSystem.repositories.CourtRepository;
import com.jakuboc.TennisCourtSystem.repositories.SurfaceTypeRepository;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class ServiceUtils {
    private ServiceUtils() {

    }

    public static boolean isValidSurfaceType(SurfaceType surfaceType, SurfaceTypeRepository surfaceTypeRepository) {
        Optional<SurfaceType> inMemorySurfaceType =
                surfaceTypeRepository.findById(surfaceType.getId());
        return inMemorySurfaceType.isPresent() &&
                Objects.equals(surfaceType, inMemorySurfaceType.get());
    }

    public static boolean isValidCourt(Court court, CourtRepository courtRepository) {
        Optional<Court> inMemoryCourt = courtRepository.findById(court.getId());
        return inMemoryCourt.isPresent() &&
                Objects.equals(court, inMemoryCourt.get());
    }

    public static boolean isOverlap(LocalDateTime date1Start, LocalDateTime date1End, LocalDateTime date2Start, LocalDateTime date2End) {
//        // making left range not included, 14:00 - 16:00 , 16:00 - 18:00 => not overlapping
//        date1End = date1End.minusMinutes(1);
//        date2End = date2End.minusMinutes(1);
        return date1Start.isBefore(date2End) && date1End.isAfter(date2Start);
    }
}
