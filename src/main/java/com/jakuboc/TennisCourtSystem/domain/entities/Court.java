package com.jakuboc.TennisCourtSystem.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="courts")
public class Court {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="court_id_seq")
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "surface_type_id", referencedColumnName = "id")
    SurfaceType surfaceType;

    String name;
}
