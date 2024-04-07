package com.jakuboc.TennisCourtSystem.controllers;

import com.jakuboc.TennisCourtSystem.Services.SurfaceTypeService;
import com.jakuboc.TennisCourtSystem.domain.dto.SurfaceTypeDto;
import com.jakuboc.TennisCourtSystem.domain.entities.SurfaceType;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * api controller for {@link SurfaceType}
 */
@RestController
@RequestMapping("/api")
public class SurfaceTypeController {
    private final SurfaceTypeService surfaceTypeService;
    private final Mapper<SurfaceType, SurfaceTypeDto> surfaceTypeMapper;

    public SurfaceTypeController(SurfaceTypeService surfaceTypeService, Mapper<SurfaceType, SurfaceTypeDto> surfaceTypeMapper) {
        this.surfaceTypeService = surfaceTypeService;
        this.surfaceTypeMapper = surfaceTypeMapper;
    }

    @GetMapping(path = "/surface-types")
    public List<SurfaceTypeDto> listSurfaces() {
        List<SurfaceType> surfaceTypes = surfaceTypeService.findAll();

        return surfaceTypes.stream()
                .map(surfaceTypeMapper::mapTo)
                .collect(Collectors.toList());
    }
}
