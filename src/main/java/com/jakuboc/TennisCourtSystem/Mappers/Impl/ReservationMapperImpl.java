package com.jakuboc.TennisCourtSystem.Mappers.Impl;

import com.jakuboc.TennisCourtSystem.Mappers.Mapper;
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
        return null;
    }

    @Override
    public Reservation mapFrom(ReservationDto reservationDto) {
        return null;
    }
}
