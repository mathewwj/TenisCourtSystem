package com.jakuboc.TennisCourtSystem.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="surface_types")
public class SurfaceType {
    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="surface_id_seq")
    Long id;

    String name;

    @Column(name = "payment_per_minute")
    double paymentPerMinute;
}
