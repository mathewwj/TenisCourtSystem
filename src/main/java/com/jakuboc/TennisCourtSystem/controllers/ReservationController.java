package com.jakuboc.TennisCourtSystem.controllers;

import com.jakuboc.TennisCourtSystem.Services.ReservationService;
import com.jakuboc.TennisCourtSystem.domain.dto.ReservationDto;
import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * api controller for {@link Reservation}
 */
@RestController
@RequestMapping("/api")
public class ReservationController {
    private final ReservationService reservationService;
    private final Mapper<Reservation, ReservationDto> reservationMapper;

    public ReservationController(ReservationService reservationService, Mapper<Reservation, ReservationDto> reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping(path = "/reservations/{id}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable("id") Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        return reservation.map(value ->
                        new ResponseEntity<>(reservationMapper.mapTo(reservation.get()), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/reservations")
    public List<ReservationDto> listSortedReservations() {
        List<Reservation> sortedReservations = reservationService.findAllSorted();
        return sortedReservations
                .stream()
                .map(reservationMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/reservations/num/{phone_number}")
    public List<ReservationDto> listUserReservations(@PathVariable("phone_number") String phoneNumber) {
        List<Reservation> filteredReservations = reservationService.findAllPhoneNumber(phoneNumber);
        return filteredReservations
                .stream()
                .map(reservationMapper::mapTo)
                .collect(Collectors.toList());
    }

    /**
     * creates reservation
     * @return price of reservation
     */
    @PostMapping(path = "/reservations")
    public ResponseEntity<Double> createReservation(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.mapFrom(reservationDto);
        reservation.setCreatedTime(LocalDateTime.now());
        Optional<Reservation> inMemoryReservation = reservationService.create(reservation);

        if (inMemoryReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Double price = inMemoryReservation.get().getPrice();
        return new ResponseEntity<>(price, HttpStatus.CREATED);
    }


    @PatchMapping(path = "/reservations/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable("id") Long id, @RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.mapFrom(reservationDto);
        Optional<Reservation> savedReservation = reservationService.partialUpdate(id, reservation);

        return savedReservation.map(value ->
                    new ResponseEntity<>(reservationMapper.mapTo(savedReservation.get()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(path = "/reservations/{id}")
    public ResponseEntity deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
