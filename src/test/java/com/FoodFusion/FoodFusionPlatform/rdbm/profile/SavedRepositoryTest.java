package com.foodFusion.foodFusionPlatform.rdbm.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@SpringBootTest
public class SavedRepositoryTest {
    double avgRating = 0.0;
    String ingredients = "rice, veggies, soy sauce";
    String name = "Stir fry";
    String recipeCuisine = "Asian";
    String steps = "make rice, cook veggies, combine";
    String comment = "Test Comment";

    @Autowired
    private SavedRepository savedRepository;

    @Autowired
    private PostedRecipeRepository postedRecipeRepository;

    @Test
    @Transactional
    public void testSaveAndRetrieveSavedEntity() {
        // Create a PostedRecipe instance
        PostedRecipe recipe = new PostedRecipe();
        recipe.setAvgRating(avgRating);
        recipe.setIngredients(ingredients);
        recipe.setName(name);
        recipe.setRecipeCuisine(recipeCuisine);
        recipe.setSteps(steps);
        postedRecipeRepository.save(recipe);

        // Create a Saved instance
        Saved saved = new Saved();
        saved.setUser_comment(comment);
        saved.setSaved_post(Collections.singletonList(recipe));
        
        // Save the Saved instance
        saved = savedRepository.save(saved);
        
        // Retrieve the Saved instance
        Saved retrievedSaved = savedRepository.findById(saved.getId()).orElse(null);
        
        // Assertions
        assertNotNull(retrievedSaved);
        assertEquals("Test Comment", retrievedSaved.getUser_comment());
        assertEquals(1, retrievedSaved.getSaved_post().size());
        assertEquals("Stir fry", retrievedSaved.getSaved_post().get(0).getName());
    }
}
