package com.jakuboc.TennisCourtSystem.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakuboc.TennisCourtSystem.domain.dto.ReservationDto;
import com.jakuboc.TennisCourtSystem.domain.entities.GameType;
import com.jakuboc.TennisCourtSystem.domain.entities.Reservation;
import com.jakuboc.TennisCourtSystem.domain.entities.User;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import com.jakuboc.TennisCourtSystem.repositories.CourtRepository;
import com.jakuboc.TennisCourtSystem.repositories.ReservationRepository;
import com.jakuboc.TennisCourtSystem.repositories.SurfaceTypeRepository;
import com.jakuboc.TennisCourtSystem.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ReservationControllerIntegrationTests {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final SurfaceTypeRepository surfaceTypeRepository;
    private final CourtRepository courtRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final Mapper<Reservation, ReservationDto> reservationMapper;

    @Autowired
    public ReservationControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, SurfaceTypeRepository surfaceTypeRepository, CourtRepository courtRepository, UserRepository userRepository, ReservationRepository reservationRepository, Mapper<Reservation, ReservationDto> reservationMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.surfaceTypeRepository = surfaceTypeRepository;
        this.courtRepository = courtRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @BeforeEach
    public void addOtherEntities() {
        TestUtils.surfaceTypes.forEach(s -> surfaceTypeRepository.save(s.getId(), s));
        TestUtils.users.forEach(u -> userRepository.save(u.getPhoneNumber(), u));
        TestUtils.courts.forEach(c -> courtRepository.save(c.getId(), c));
    }

    @Test
    public void testThatCreateReservationWorks() throws Exception {
        Reservation reservation = TestUtils.reservations.get(0);
        String reservationJson = objectMapper.writeValueAsString(reservationMapper.mapTo(reservation));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reservationJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.content().string(reservation.getPrice().toString())
        );
    }

    @Test
    public void testThatSameTimeReservationReservationFails() throws Exception {
        Reservation reservation = TestUtils.reservations.get(0);
        reservationRepository.save(reservation.getId(), reservation);
        Reservation reservationDup = TestUtils.reservations.get(2);
        String reservationJson = objectMapper.writeValueAsString(reservationMapper.mapTo(reservationDup));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reservationJson)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
    }

    @Test
    public void testThatSameTimeReservationOtherCourtReservationWorks() throws Exception {
        Reservation reservation = TestUtils.reservations.get(0);
        reservationRepository.save(reservation.getId(), reservation);
        Reservation reservationDup = TestUtils.reservations.get(1);
        String reservationJson = objectMapper.writeValueAsString(reservationMapper.mapTo(reservationDup));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reservationJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.content().string(reservationDup.getPrice().toString())
        );
    }

    @Test
    public void testThatGetOneReservationWorks() throws Exception {
        Reservation reservation = TestUtils.reservations.get(0);
        reservationRepository.save(reservation.getId(), reservation);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/reservations/" + reservation.getId())
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                jsonPath("$.id").isNumber()
        ).andExpect(
                jsonPath("$.id").value(reservation.getId())
        ).andExpect(
                jsonPath("$.court").exists()
        );
    }
    @Test
    public void testThatReturnAllReservationWorks() throws Exception {
        Reservation reservation = TestUtils.reservations.get(0);
        Reservation reservation1 = TestUtils.reservations.get(1);
        reservationRepository.save(reservation.getId(), reservation);
        reservationRepository.save(reservation1.getId(), reservation1);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/reservations")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                jsonPath("$").isArray()
        ).andExpect(
                jsonPath("$", hasSize(2))
        ).andExpect(
                jsonPath("$[1].createdTime", greaterThanOrEqualTo("$[0].createdTime"))
        );
    }

    @Test
    public void testThatReturnUserReservationWorks() throws Exception {
        Reservation reservation = TestUtils.reservations.get(0);
        Reservation reservation1 = TestUtils.reservations.get(1);
        reservationRepository.save(reservation.getId(), reservation);
        reservationRepository.save(reservation1.getId(), reservation1);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/reservations/num/" + reservation.getUser().getPhoneNumber())
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                jsonPath("$").isArray()
        ).andExpect(
                jsonPath("$", hasSize(1))
        ).andExpect(
                jsonPath("$[0].user.phoneNumber").value(reservation.getUser().getPhoneNumber())
        );
    }

    @Test
    public void testThatReservationCourtWorks() throws Exception {
        Reservation reservation = TestUtils.reservations.get(0);
        reservationRepository.save(reservation.getId(), reservation);
        reservation.setGameType(GameType.DOUBLES);
        String courtJson = objectMapper.writeValueAsString(reservationMapper.mapTo(reservation));

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/reservations/" + reservation.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courtJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                jsonPath("$.gameType").value(reservation.getGameType().toString())
        );
    }

    @Test
    public void testThatCreateReservationNewUserWorks() throws Exception {
        Reservation reservation = TestUtils.reservations.get(2);
        User user = new User("420", "Guandale", "Dingle");
        reservation.setUser(user);
        String courtJson = objectMapper.writeValueAsString(reservationMapper.mapTo(reservation));
        reservation.setUser(TestUtils.users.get(0));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courtJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

        Assertions.assertThat(userRepository.findById(user.getPhoneNumber())).isPresent();
    }

    @Test
    public void testThatUpdateReservationNewUserWorks() throws Exception {
        Reservation reservation = TestUtils.reservations.get(2);
        reservationRepository.save(reservation.getId(), reservation);
        User user = new User("420", "Guandale", "Dingle");
        reservation.setUser(user);
        String courtJson = objectMapper.writeValueAsString(reservationMapper.mapTo(reservation));
        reservation.setUser(TestUtils.users.get(0));

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/reservations/" + reservation.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courtJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                jsonPath("$.user.phoneNumber").value(user.getPhoneNumber())
        );

        Assertions.assertThat(userRepository.findById(user.getPhoneNumber())).isPresent();
    }


    @Test
    public void testThatDeleteReservationWorks() throws Exception {
        Reservation reservation = TestUtils.reservations.get(0);
        reservationRepository.save(reservation.getId(), reservation);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/reservations/" + reservation.getId())
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/reservations")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                jsonPath("$").isArray()
        ).andExpect(
                jsonPath("$", hasSize(0))
        );
    }

}
