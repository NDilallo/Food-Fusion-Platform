package com.foodFusion.foodFusionPlatform.services.upload;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodFusion.foodFusionPlatform.rdbm.upload.Draft;
import com.foodFusion.foodFusionPlatform.rdbm.upload.DraftRepository;

/**
 * Test out the service
 */
@SpringBootTest
public class DraftServiceTest {
    @Autowired
    private DraftService service;

    @Autowired
    private DraftRepository recipeRepo;


    @BeforeEach
    public void addRecipe() {
        Draft recipe1 = new Draft();
        recipe1.setIngredients("rice, veggies, soy sauce");
        recipe1.setRecipeName("Best Stir fry");
        recipe1.setCuisine("Asian");
        recipe1.setDescription("make rice, cook veggies, combine");

        service.save(recipe1);
    }

    @Test
    public void testAddRestaurant() {
        Draft recipe2 = new Draft();
        recipe2.setIngredients("burger, buns, cheese");
        recipe2.setRecipeName("Cheeseburger");
        recipe2.setCuisine("American");
        recipe2.setDescription("make hamburger, put on cheese");
        long b4 = service.list().size();
        service.save(recipe2);
        long after = service.list().size();
        // since adding, # of courses should be the 1 more than before
        assertEquals(b4 + 1, after);
    }   
    
    @Test
    public void testUpdateCourse() {
        // In order to update, need to find something so will be updating the first one
        List<Draft> recipes = service.list();
        Draft recipe = recipes.get(0);
        recipe.setCuisine("East Asian");       
        
        service.save(recipe);
        long after = service.list().size();
        // since updating, # of courses should be the same
        assertEquals(recipes.size(), after);
    }

    @Test
    public void testDelete() {
        // In order to delete, need to find something to delete so will delete the first one
        List<Draft> recipes = service.list();
        service.delete(recipes.get(0).getRecipeId());
        long after = service.list().size();
        // since removing 1, the list should be 1 less than original list
        assertEquals(recipes.size() - 1, after);
    }
}
