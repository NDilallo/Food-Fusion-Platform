package com.foodFusion.foodFusionPlatform.services.upload;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foodFusion.foodFusionPlatform.rdbm.upload.Ingredients;
import com.foodFusion.foodFusionPlatform.rdbm.upload.IngredientsRepository;

class IngredientsServiceTest {

    @Mock
    private IngredientsRepository ingredientsRepository;

    @InjectMocks
    private IngredientsService ingredientsService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddIngredient() {
        Ingredients ingredient = new Ingredients();
        ingredient.setId(1L);
        ingredient.setProtein("Tofu");

        when(ingredientsRepository.save(ingredient)).thenReturn(ingredient);

        Ingredients savedIngredient = ingredientsService.addIngredient(ingredient);

        assertThat(savedIngredient).isNotNull();
        assertThat(savedIngredient.getId()).isEqualTo(1L);
        assertThat(savedIngredient.getProtein()).isEqualTo("Tofu");

        verify(ingredientsRepository, times(1)).save(ingredient);
    }

    @Test
    void testGetAllIngredients() {
        List<Ingredients> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredients(1L, "Tofu", "French Beans", "Gluten-Free"));
        ingredientsList.add(new Ingredients(2L, "Soya Chunks", "Broccoli", "Vegetarian"));

        when(ingredientsRepository.findAll()).thenReturn(ingredientsList);

        List<Ingredients> retrievedIngredients = ingredientsService.getAllIngredients();

        assertThat(retrievedIngredients).isNotNull();
        assertThat(retrievedIngredients.size()).isEqualTo(2);
        assertThat(retrievedIngredients.get(0).getId()).isEqualTo(1L);
        assertThat(retrievedIngredients.get(1).getId()).isEqualTo(2L);
        assertThat(retrievedIngredients.get(0).getProtein()).isEqualTo("Tofu");
        assertThat(retrievedIngredients.get(1).getProtein()).isEqualTo("Soya Chunks");

        verify(ingredientsRepository, times(1)).findAll();
    }
}

