package com.jakuboc.TennisCourtSystem.mappers.impl;

import com.jakuboc.TennisCourtSystem.domain.dto.SurfaceTypeDto;
import com.jakuboc.TennisCourtSystem.domain.entities.SurfaceType;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SurfaceMapperImpl implements Mapper<SurfaceType, SurfaceTypeDto> {
    private final ModelMapper modelMapper;

    public SurfaceMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SurfaceTypeDto mapTo(SurfaceType surfaceType) {
        return modelMapper.map(surfaceType, SurfaceTypeDto.class);
    }

    @Override
    public SurfaceType mapFrom(SurfaceTypeDto surfaceTypeDto) {
        return modelMapper.map(surfaceTypeDto, SurfaceType.class);
    }
}
