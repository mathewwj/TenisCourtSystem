package com.jakuboc.TennisCourtSystem.domain.dto;

import com.jakuboc.TennisCourtSystem.domain.entities.GameType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {
    Long id;

    UserDto user;

    CourtDto court;

    GameType gameType;

    LocalDateTime startTime;

    LocalDateTime endTime;

    LocalDateTime createdTime;
}
