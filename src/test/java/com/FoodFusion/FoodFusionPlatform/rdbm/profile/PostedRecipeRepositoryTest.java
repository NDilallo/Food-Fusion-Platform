package com.foodFusion.foodFusionPlatform.rdbm.profile;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostedRecipeRepositoryTest {
    @Autowired
    private PostedRecipeRepository repo;
/* 
    @BeforeEach
    public void addRecipes() {
        PostedRecipe recipe1 = new PostedRecipe();
        recipe1.setAvgRating(3.5);
        recipe1.setIngredients("rice, veggies, soy sauce");
        recipe1.setName("Stir fry");
        recipe1.setRecipeCuisine("Asian");
        recipe1.setSteps("make rice, cook veggies, combine");
        repo.save(recipe1);
        
        
    }
    */
     /**
     * Test create, read, update, and delete
     */
    @Test
    @Order(1)
    public void testCrud() {
        PostedRecipe rtest = new PostedRecipe();
        rtest.setAvgRating(0.0);
        rtest.setIngredients("rice, veggies, soy sauce");
        rtest.setName("Stir fry");
        rtest.setRecipeCuisine("Asian");
        rtest.setSteps("make rice, cook veggies, combine");

        long b4count = repo.count();
        System.out.println(b4count);
        long b4id = rtest.getRecipeId();
        repo.save(rtest);
        long aftercount = repo.count();
        System.out.println(aftercount);
        long afterid = rtest.getRecipeId();

        // there should be 1 more in the database after the save
        assertEquals(b4count + 1, aftercount);

        // original id was 0 but afterwards the id was generated and so should not be equal
        assertNotEquals(b4id, afterid);

        // Scenario of updating cross listing
        // Be sure to find the reference from the database before the update
        PostedRecipe updated = repo.findByRecipeId(afterid);
        updated.setName("Veggie Stir Fry");
        repo.save(updated);

        PostedRecipe updatedCheck = repo.findByRecipeId(afterid);
        assertNotEquals(updatedCheck, rtest);
        assertEquals("Veggie Stir Fry", updatedCheck.getName());

        b4count = repo.count();
        repo.delete(updatedCheck);
        aftercount = repo.count();
        assertEquals(b4count - 1, aftercount);

    }   

    @Test
    @Order(2)
    public void findByName() {
        PostedRecipe recipe2 = new PostedRecipe();
        recipe2.setAvgRating(2.5);
        recipe2.setIngredients("burger, buns, cheese");
        recipe2.setName("Cheeseburger");
        recipe2.setRecipeCuisine("American");
        recipe2.setSteps("make hamburger, put on cheese");
        repo.save(recipe2);

        PostedRecipe recipe3 = new PostedRecipe();
        recipe3.setAvgRating(5.0);
        recipe3.setIngredients("veggie burger, cheese, buns");
        recipe3.setName("Cheeseburger");
        recipe3.setRecipeCuisine("American");
        recipe3.setSteps("make veggie burger, put on cheese");
        repo.save(recipe3);

        PostedRecipe rtest = new PostedRecipe();
        rtest.setAvgRating(0.0);
        rtest.setIngredients("rice, veggies, soy sauce");
        rtest.setName("Stir fry");
        rtest.setRecipeCuisine("Asian");
        rtest.setSteps("make rice, cook veggies, combine");
        repo.save(rtest);

        long count = repo.count();
        System.out.println(count);
        List<PostedRecipe> cheeseburger = repo.findByName("Cheeseburger");
        List<PostedRecipe> stirFry = repo.findByName("Stir fry");
        List<PostedRecipe> chicken = repo.findByName("Chicken");

        assertNotEquals(0, count);
        assertEquals(2, cheeseburger.size());
        assertEquals(1, stirFry.size());
        assertEquals(0, chicken.size());
    }

    @Test
    @Order(3)
    public void findByCuisine() {
        PostedRecipe recipe2 = new PostedRecipe();
        recipe2.setAvgRating(2.5);
        recipe2.setIngredients("burger, buns, cheese");
        recipe2.setName("Burger");
        recipe2.setRecipeCuisine("USA");
        recipe2.setSteps("make hamburger, put on cheese");
        repo.save(recipe2);

        PostedRecipe recipe3 = new PostedRecipe();
        recipe3.setAvgRating(5.0);
        recipe3.setIngredients("veggie burger, cheese, buns");
        recipe3.setName("Burger");
        recipe3.setRecipeCuisine("USA");
        recipe3.setSteps("make veggie burger, put on cheese");
        repo.save(recipe3);


        long count = repo.count();
        System.out.println(count);
        List<PostedRecipe> american = repo.findByRecipeCuisine("USA");
        List<PostedRecipe> ethiopian = repo.findByRecipeCuisine("Ethiopian");

        assertNotEquals(0, count);
        assertEquals(2, american.size());
        assertEquals(0, ethiopian.size());
    }

}
