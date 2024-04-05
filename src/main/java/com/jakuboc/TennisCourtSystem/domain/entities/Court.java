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
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="court_id_seq")
    @Id
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "surface_type_id", referencedColumnName = "id")
    SurfaceType surfaceType;

    String name;
}
