package com.jakuboc.TennisCourtSystem.Services.Impl;

import com.jakuboc.TennisCourtSystem.Services.ReservationService;
import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;
import com.jakuboc.TennisCourtSystem.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    public final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findAllSorted() {
        return reservationRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Reservation::getCreatedTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findAllPhoneNumber(String phoneNumber) {
        LocalDateTime now = LocalDateTime.now();
        return findAllSorted().stream()
                .filter(r -> r.getUser().getPhoneNumber().equals(phoneNumber)
                        && now.isBefore(r.getStartTime()))
                .collect(Collectors.toList());
    }
}
