package com.foodFusion.foodFusionPlatform.controller.homePage;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.Chefs;
import com.foodFusion.foodFusionPlatform.services.homePage.ChefsService;

@WebMvcTest(ChefsController.class)
public class ChefsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChefsService chefsService;

    @Autowired
    private ObjectMapper objectMapper;

    private Chefs chef;

    @BeforeEach
    void setUp() {
        chef = new Chefs(1L, "Gordon Ramsay", "International Cuisine");
    }

    @Test
    void testAddChef() throws Exception {
        given(chefsService.addChef((chef))).willReturn(chef);

        mockMvc.perform(post("/chefs/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(chef)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gordon Ramsay"));
    }

    @Test
    void testGetAllChefs() throws Exception {
        given(chefsService.getAllChefs()).willReturn(Collections.singletonList(chef));

        mockMvc.perform(get("/chefs/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gordon Ramsay"));
    }

    @Test
    void testGetChefByName() throws Exception {
        given(chefsService.getChefByName("Gordon Ramsay")).willReturn(chef);

        mockMvc.perform(get("/chefs/find").param("name", "Gordon Ramsay"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gordon Ramsay"));
    }
}
