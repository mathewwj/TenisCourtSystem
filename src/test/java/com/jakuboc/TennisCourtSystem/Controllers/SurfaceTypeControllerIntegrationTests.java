package com.jakuboc.TennisCourtSystem.Controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakuboc.TennisCourtSystem.domain.dto.SurfaceTypeDto;
import com.jakuboc.TennisCourtSystem.domain.entities.SurfaceType;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import com.jakuboc.TennisCourtSystem.repositories.SurfaceTypeRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class SurfaceTypeControllerIntegrationTests {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final SurfaceTypeRepository surfaceTypeRepository;
    private final Mapper<SurfaceType, SurfaceTypeDto> surfaceTypeMapper;

    @Autowired
    public SurfaceTypeControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, SurfaceTypeRepository surfaceTypeRepository, Mapper<SurfaceType, SurfaceTypeDto> surfaceTypeMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.surfaceTypeRepository = surfaceTypeRepository;
        this.surfaceTypeMapper = surfaceTypeMapper;
    }

    @Test
    public void testThatReturnAllSurfaceTypesWorks() throws Exception {
        TestUtils.surfaceTypes.forEach(s -> surfaceTypeRepository.save(s.getId(), s));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/surface-types")
        ).andExpect(
                jsonPath("$").isArray()
        ).andExpect(
                jsonPath("$", hasSize(3))
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
}
