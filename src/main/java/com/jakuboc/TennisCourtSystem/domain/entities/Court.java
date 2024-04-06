package com.jakuboc.TennisCourtSystem.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="courts")
@SoftDelete
public class Court {
    @Id
    Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "surface_type_id", referencedColumnName = "id")
    SurfaceType surfaceType;

    String name;
}
