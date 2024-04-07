package com.jakuboc.TennisCourtSystem.Controllers;

import com.jakuboc.TennisCourtSystem.domain.entities.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TestUtils {
    private TestUtils() {

    }
    public static final List<User> users = Arrays.asList(
            new User("+987654321", "Jane", "Smith"),
            new User("+421123456789", "Tomáš", "Novák"),
            new User("+420987654321", "Petr", "Svoboda")
    );


    public static final List<SurfaceType> surfaceTypes = Arrays.asList(
            new SurfaceType(1L, "Hard Court", 0.05),
            new SurfaceType(2L, "Clay Court", 0.03),
            new SurfaceType(3L, "Grass Court", 0.07)
    );

    public static final List<Court> courts = Arrays.asList(
            new Court(1L, surfaceTypes.get(0), "Court 1"),
            new Court(2L, surfaceTypes.get(1), "Court 2"),
            new Court(3L, surfaceTypes.get(2), "Court 3")
    );

    public static final List<Reservation> reservations = Arrays.asList(
            new Reservation(1L, users.get(0), courts.get(0), GameType.SINGLES, LocalDateTime.of(2024, 4, 4, 16, 0), LocalDateTime.of(2024, 4, 4, 17, 0), LocalDateTime.of(2024, 4, 4, 10, 0)),
            // overlapping
            new Reservation(2L, users.get(1), courts.get(1), GameType.DOUBLES, LocalDateTime.of(2024, 4, 4, 15, 30), LocalDateTime.of(2024, 4, 4, 16, 30), LocalDateTime.of(2024, 4, 4, 1, 0))
    );
}
