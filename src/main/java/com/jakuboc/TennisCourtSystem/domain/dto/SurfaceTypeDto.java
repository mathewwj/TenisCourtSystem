package com.jakuboc.TennisCourtSystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurfaceTypeDto {
    Long id;

    String name;

    double paymentPerMinute;
}
