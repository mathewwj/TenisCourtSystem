package com.jakuboc.TennisCourtSystem.mappers.impl;

import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import com.jakuboc.TennisCourtSystem.domain.dto.ReservationDto;
import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapperImpl  implements Mapper<Reservation, ReservationDto> {
    private final ModelMapper modelMapper;

    public ReservationMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ReservationDto mapTo(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }

    @Override
    public Reservation mapFrom(ReservationDto reservationDto) {
        return modelMapper.map(reservationDto, Reservation.class);
    }
}
