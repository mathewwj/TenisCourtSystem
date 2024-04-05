package com.jakuboc.TennisCourtSystem.controllers;

import com.jakuboc.TennisCourtSystem.Services.ReservationService;
import com.jakuboc.TennisCourtSystem.domain.dto.ReservationDto;
import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationController {
    private final ReservationService reservationService;
    private final Mapper<Reservation, ReservationDto> reservationMapper;

    public ReservationController(ReservationService reservationService, Mapper<Reservation, ReservationDto> reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }


    @GetMapping(path = "/api/reservations")
    public List<ReservationDto> listSortedReservations() {
        List<Reservation> sortedReservations = reservationService.findAllSorted();
        return sortedReservations
                .stream()
                .map(reservationMapper::mapTo)
                .collect(Collectors.toList());
    }

}
