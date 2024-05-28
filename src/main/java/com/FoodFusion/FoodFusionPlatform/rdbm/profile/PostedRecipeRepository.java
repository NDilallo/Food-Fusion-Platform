package com.foodFusion.foodFusionPlatform.rdbm.profile;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostedRecipeRepository extends CrudRepository<PostedRecipe, Long> {
    public List<PostedRecipe> findByName(String name);

    public PostedRecipe findByRecipeId(long recipeId);

    public List<PostedRecipe> findByRecipeCuisine(String recipeCuisine);
}
