package com.jakuboc.TennisCourtSystem.controllers;

import com.jakuboc.TennisCourtSystem.Services.CourtService;
import com.jakuboc.TennisCourtSystem.domain.dto.CourtDto;
import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
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
    @GetMapping(path = "/api/courts/{id}")
    public ResponseEntity<CourtDto> getCourt(@PathVariable("id") Long id) {
        Optional<Court> court = courtService.findById(id);

        return court.map(value ->
                        new ResponseEntity<>(courtMapper.mapTo(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
