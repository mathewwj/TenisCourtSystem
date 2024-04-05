package com.jakuboc.TennisCourtSystem.controllers;

import com.jakuboc.TennisCourtSystem.Services.CourtService;
import com.jakuboc.TennisCourtSystem.domain.dto.CourtDto;
import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourtController {
    private final CourtService courtService;
    private final Mapper<Court, CourtDto> courtMapper;

    public CourtController(CourtService courtService, Mapper<Court, CourtDto> courtMapper) {
        this.courtService = courtService;
        this.courtMapper = courtMapper;
    }

    @GetMapping(path = "/api/courts")
    public List<CourtDto> listCourts() {
        List<Court> courts = courtService.findAll();

        return courts.stream()
                .map(courtMapper::mapTo)
                .collect(Collectors.toList());
    }
}
