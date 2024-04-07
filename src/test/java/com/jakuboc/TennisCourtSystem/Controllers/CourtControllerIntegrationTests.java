package com.jakuboc.TennisCourtSystem.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakuboc.TennisCourtSystem.Services.CourtService;
import com.jakuboc.TennisCourtSystem.domain.dto.CourtDto;
import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import com.jakuboc.TennisCourtSystem.repositories.CourtRepository;
import com.jakuboc.TennisCourtSystem.repositories.SurfaceTypeRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CourtControllerIntegrationTests {
    private final CourtService courtService;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final SurfaceTypeRepository surfaceTypeRepository;
    private final CourtRepository courtRepository;
    private final Mapper<Court, CourtDto> courtMapper;

    @Autowired
    public CourtControllerIntegrationTests(CourtService courtService, MockMvc mockMvc, SurfaceTypeRepository surfaceTypeRepository, CourtRepository courtRepository, Mapper<Court, CourtDto> courtMapper) {
        this.courtService = courtService;
        this.mockMvc = mockMvc;
        this.surfaceTypeRepository = surfaceTypeRepository;
        this.courtRepository = courtRepository;
        this.courtMapper = courtMapper;
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    public void addSurfaces() {
        for (var s : TestUtils.surfaceTypes) {
            surfaceTypeRepository.save(s.getId(), s);
        }
    }

    @Test
    public void testThatCreateCourtSuccessfullyReturnsSavedCourt() throws Exception {
        Court court = TestUtils.courts.get(0);
        String courtJson = objectMapper.writeValueAsString(courtMapper.mapTo(court));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/courts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courtJson)
        ).andExpect(
                jsonPath("$.id").isNumber()
        ).andExpect(
                jsonPath("$.surfaceTypeDto").exists()
        ).andExpect(
                jsonPath("$.name").value(court.getName())
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreatedDuplicateCourtFails() throws Exception {
        Court court = TestUtils.courts.get(0);
        courtRepository.save(1L, TestUtils.courts.get(0));
        String courtJson = objectMapper.writeValueAsString(courtMapper.mapTo(court));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/courts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courtJson)
        ).andExpect(
                content().string("")
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
    }

    @Test
    public void testThatGetOneCourtWorks() throws Exception {
        Court court = TestUtils.courts.get(0);
        courtRepository.save(1L, court);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/courts/" + court.getId())
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                jsonPath("$.id").isNumber()
        ).andExpect(
                jsonPath("$.surfaceTypeDto").exists()
        ).andExpect(
                jsonPath("$.name").value(court.getName())
        );
    }

    @Test
    public void testThatReturnAllCourtWorks() throws Exception {
        courtRepository.save(1L, TestUtils.courts.get(0));
        courtRepository.save(2L, TestUtils.courts.get(1));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/courts")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                jsonPath("$").isArray()
        ).andExpect(
                jsonPath("$", hasSize(2))
        );
    }
    @Test
    public void testThatDeleteCourtWorks() throws Exception {
        Court court = TestUtils.courts.get(0);
        courtRepository.save(1L, court);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/courts/" + court.getId())
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/courts")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                jsonPath("$").isArray()
        ).andExpect(
                jsonPath("$", hasSize(0))
        );
    }

    @Test
    public void testThatUpdateCourtWorks() throws Exception {
        Court court = TestUtils.courts.get(0);
        courtRepository.save(1L, court);
        court.setName("updated name");
        String courtJson = objectMapper.writeValueAsString(courtMapper.mapTo(court));

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/courts/" + court.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courtJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                jsonPath("$.id").isNumber()
        ).andExpect(
                jsonPath("$.surfaceTypeDto").exists()
        ).andExpect(
                jsonPath("$.name").value(court.getName())
        );
    }

    @Test
    public void testThatUpdateInvalidCourtFails() throws Exception {
        Court court = TestUtils.courts.get(0);
        courtRepository.save(1L, court);
        court.getSurfaceType().setId(69L);
        String courtJson = objectMapper.writeValueAsString(courtMapper.mapTo(court));

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/courts/" + court.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courtJson)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );

        Optional<Court> courtOrig = courtRepository.findById(1L);
        Assertions.assertThat(courtOrig).isPresent();
        Assertions.assertThat(courtOrig.get().getSurfaceType().getId()).isEqualTo(1L);
    }
}
