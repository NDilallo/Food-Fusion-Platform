package com.FoodFusion.FoodFusionPlatform.services.profile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.FoodFusion.FoodFusionPlatform.rdbm.profile.PostedRecipe;
import com.FoodFusion.FoodFusionPlatform.services.PostedRecipeService;

/**
 * Test out the service
 */
@SpringBootTest
public class PostedRecipeServiceTest {
    @Autowired
    private PostedRecipeService service;

    @BeforeEach
    public void addRecipe() {
        PostedRecipe recipe1 = new PostedRecipe();
        recipe1.setAvgRating(3.5);
        recipe1.setIngredients("rice, veggies, soy sauce");
        recipe1.setName("Stir fry");
        recipe1.setRecipeCuisine("Asian");
        recipe1.setSteps("make rice, cook veggies, combine");
        service.save(recipe1);
    }

    @Test
    public void testAddRestaurant() {
        PostedRecipe recipe2 = new PostedRecipe();
        recipe2.setAvgRating(2.5);
        recipe2.setIngredients("burger, buns, cheese");
        recipe2.setName("Cheeseburger");
        recipe2.setRecipeCuisine("American");
        recipe2.setSteps("make hamburger, put on cheese");
        long b4 = service.list().size();
        service.save(recipe2);
        long after = service.list().size();
        // since adding, # of courses should be the 1 more than before
        assertEquals(b4 + 1, after);
    }   
    
    @Test
    public void testUpdateCourse() {
        // In order to update, need to find something so will be updating the first one
        List<PostedRecipe> recipes = service.list();
        PostedRecipe recipe = recipes.get(0);
        recipe.setAvgRating(4.0);        
        
        service.save(recipe);
        long after = service.list().size();
        // since updating, # of courses should be the same
        assertEquals(recipes.size(), after);
    }

    @Test
    public void testDelete() {
        // In order to delete, need to find something to delete so will delete the first one
        List<PostedRecipe> recipes = service.list();
        service.delete(recipes.get(0).getRecipeId());
        long after = service.list().size();
        // since removing 1, the list should be 1 less than original list
        assertEquals(recipes.size() - 1, after);
    }
}
