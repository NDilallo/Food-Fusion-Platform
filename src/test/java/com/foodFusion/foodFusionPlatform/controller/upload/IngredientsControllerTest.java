package com.foodFusion.foodFusionPlatform.controller.upload;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodFusion.foodFusionPlatform.rdbm.upload.Ingredients;
import com.foodFusion.foodFusionPlatform.rdbm.upload.IngredientsRepository;

@WebMvcTest(IngredientsController.class)
public class IngredientsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientsRepository ingredientsRepository;

    @InjectMocks
    private IngredientsController ingredientsController;

    @Test
    void testAddProtein() throws Exception {
        Ingredients ingredients = new Ingredients();
        ingredients.setProtein("Beef");

        when(ingredientsRepository.save(ingredients)).thenReturn(ingredients);

        mockMvc.perform(MockMvcRequestBuilders.post("/ingredients/addProtein")
                .param("protein", "Beef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ingredients)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Protein added successfully!"));
    }

    @Test
    void testAddVeggies() throws Exception {
        Ingredients ingredients = new Ingredients();
        ingredients.setVeggies("Broccoli");

        when(ingredientsRepository.save(ingredients)).thenReturn(ingredients);

        mockMvc.perform(MockMvcRequestBuilders.post("/ingredients/addVeggies")
                .param("veggies", "Broccoli")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ingredients)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("All Vegetables added successfully!"));
    }

    @Test
    void testAddDietaryRestrictions() throws Exception {
        Ingredients ingredients = new Ingredients();
        ingredients.setDietaryRestrictions("Vegan");

        when(ingredientsRepository.save(ingredients)).thenReturn(ingredients);

        mockMvc.perform(MockMvcRequestBuilders.post("/ingredients/addDietaryRestrictions")
                .param("dietaryRestrictions", "Vegan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ingredients)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The Dietary restrictions added successfully!"));
    }

    @Test
    void testCheckDietaryRestrictions() throws Exception {
        Ingredients ingredients = new Ingredients();
        ingredients.setDietaryRestrictions("Gluten-Free");

        when(ingredientsRepository.findById(1L)).thenReturn(Optional.of(ingredients));

        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/checkDietaryRestrictions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ingredients)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("This dish has dietary restrictions."));
    }

    @Test
    void testCheckVegetarian() throws Exception {
        Ingredients ingredients = new Ingredients();
        ingredients.setDietaryRestrictions("Vegetarian");

        when(ingredientsRepository.findById(1L)).thenReturn(Optional.of(ingredients));

        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/checkVegetarian")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ingredients)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("This dish is not vegetarian."));
    }

    @Test
    void testCheckGlutenFree() throws Exception {
        Ingredients ingredients = new Ingredients();
        ingredients.setDietaryRestrictions("Gluten-Free");

        when(ingredientsRepository.findById(1L)).thenReturn(Optional.of(ingredients));

        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/checkGlutenFree")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ingredients)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("This dish is not gluten-free."));
    }

    @Test
    void testCheckLactoseDairyFree() throws Exception {
        Ingredients ingredients = new Ingredients();
        ingredients.setDietaryRestrictions("Lactose-Free");

        when(ingredientsRepository.findById(1L)).thenReturn(Optional.of(ingredients));

        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/checkLactoseDairyFree")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ingredients)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("This dish is not lactose and Dairy-free."));
    }
}

