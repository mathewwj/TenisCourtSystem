package com.jakuboc.TennisCourtSystem.Services.Impl;

import com.jakuboc.TennisCourtSystem.Services.ReservationService;
import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;
import com.jakuboc.TennisCourtSystem.domain.entities.User;
import com.jakuboc.TennisCourtSystem.repositories.CourtRepository;
import com.jakuboc.TennisCourtSystem.repositories.ReservationRepository;
import com.jakuboc.TennisCourtSystem.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    public final ReservationRepository reservationRepository;
    public final CourtRepository courtRepository;
    public final UserRepository userRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, CourtRepository courtRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.courtRepository = courtRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
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

    @Override
    public Optional<Reservation> create(Reservation reservation) {
        var startTime = reservation.getStartTime();
        var endTime = reservation.getEndTime();
        if (!startTime.isBefore(endTime)) {
            return Optional.empty();
        }

        boolean hasOverlaps = findAll()
                .stream()
                .anyMatch(r -> ServiceUtils.isOverlap(startTime, endTime, r.getStartTime(), r.getEndTime()));

        if (hasOverlaps || !ServiceUtils.isValidCourt(reservation.getCourt(), courtRepository)) {
            return Optional.empty();
        }

        User user = reservation.getUser();
        if (!userRepository.isExists(user.getPhoneNumber())
                && !reservationRepository.isExists(reservation.getId())) {
            userRepository.save(user.getPhoneNumber(), user);
        }

        return reservationRepository.save(reservation.getId(), reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
