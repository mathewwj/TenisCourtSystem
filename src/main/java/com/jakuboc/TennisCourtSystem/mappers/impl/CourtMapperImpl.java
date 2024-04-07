package com.jakuboc.TennisCourtSystem.mappers.impl;

import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import com.jakuboc.TennisCourtSystem.domain.dto.CourtDto;
import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CourtMapperImpl implements Mapper<Court, CourtDto> {
    private final ModelMapper modelMapper;

    public CourtMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CourtDto mapTo(Court court) {
        return modelMapper.map(court, CourtDto.class);
    }

    @Override
    public Court mapFrom(CourtDto courtDto) {
        return modelMapper.map(courtDto, Court.class);
    }
}
