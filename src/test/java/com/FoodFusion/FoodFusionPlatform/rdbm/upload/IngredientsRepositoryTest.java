package com.foodFusion.foodFusionPlatform.rdbm.upload;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodFusion.foodFusionPlatform.controller.upload.IngredientsController;

@WebMvcTest(IngredientsController.class)
public class IngredientsRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Ingredients ingredients;

    @BeforeEach
    void setUp() {
        ingredients = new Ingredients(1L, "Tofu", "French Beans", "Gluten-Free");
    }

    @Test
    void testFindByProtein() throws Exception {
        given(ingredientsRepository.findByProtein("Tofu")).willReturn(ingredients);
    
        mockMvc.perform(post("/ingredients/addProtein")
                .param("protein", "Tofu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredients)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.protein").value("Tofu"));
    }
    

    @Test
    void testFindByVeggies() throws Exception {
        given(ingredientsRepository.findByVeggies("French Beans")).willReturn(ingredients);

        mockMvc.perform(get("/ingredients/addVeggies")
                .param("veggies", "French Beans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredients)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.veggies").value("French Beans"));
    }

    @Test
    void testFindByDietaryRestrictions() throws Exception {
        given(ingredientsRepository.findByDietaryRestrictions("Gluten-Free")).willReturn(ingredients);

        mockMvc.perform(get("/ingredients/addDietaryRestrictions")
                .param("dietaryRestrictions", "Gluten-Free")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredients)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dietaryRestrictions").value("Gluten-Free"));
    }
}
