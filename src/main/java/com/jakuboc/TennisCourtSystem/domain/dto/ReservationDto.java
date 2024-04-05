package com.jakuboc.TennisCourtSystem.domain.dto;

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

    boolean isSingle;

    LocalDateTime startTime;

    LocalDateTime endTime;

    LocalDateTime createdTime;
}
