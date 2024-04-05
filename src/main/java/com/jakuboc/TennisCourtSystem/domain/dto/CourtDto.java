package com.jakuboc.TennisCourtSystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourtDto {
    Long id;

    SurfaceTypeDto surfaceType;

    String name;
}
