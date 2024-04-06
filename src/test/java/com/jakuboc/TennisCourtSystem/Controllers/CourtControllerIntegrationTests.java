package com.jakuboc.TennisCourtSystem.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakuboc.TennisCourtSystem.Services.CourtService;
import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import com.jakuboc.TennisCourtSystem.domain.entities.SurfaceType;
import com.jakuboc.TennisCourtSystem.repositories.SurfaceTypeRepository;
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

import java.util.List;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CourtControllerIntegrationTests {
    private final CourtService courtService;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final SurfaceTypeRepository surfaceTypeRepository;

    @Autowired
    public CourtControllerIntegrationTests(CourtService courtService, MockMvc mockMvc, SurfaceTypeRepository surfaceTypeRepository) {
        this.courtService = courtService;
        this.mockMvc = mockMvc;
        this.surfaceTypeRepository = surfaceTypeRepository;
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
        String courtJson = objectMapper.writeValueAsString(court);
        List<SurfaceType> all = surfaceTypeRepository.findAll();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/courts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courtJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.surface").exists()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(court.getName())
        );
    }


}
