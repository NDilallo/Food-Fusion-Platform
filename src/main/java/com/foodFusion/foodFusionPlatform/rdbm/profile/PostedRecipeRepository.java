package com.foodFusion.foodFusionPlatform.rdbm.profile;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Marisa Ban
 * This class defines the repository for the PostedRecipes table.
 * 
 */
public interface PostedRecipeRepository extends CrudRepository<PostedRecipe, Long> {
    /**
     * Finds posted recipe(s) with the given name 
     * @param name
     * @return a list of PostedRecipe instances
     */
    public List<PostedRecipe> findByName(String name);

    /**
     * Finds a PostedRecipe with the given id
     * @param recipeId
     * @return an instance of PostedRecipe
     */
    public PostedRecipe findByRecipeId(long recipeId);

    /**
     * Finds posted recipe(s) with the given cuisine type
     * @param recipeCuisine
     * @return a list of PostedRecipe instances
     */
    public List<PostedRecipe> findByRecipeCuisine(String recipeCuisine);
}
