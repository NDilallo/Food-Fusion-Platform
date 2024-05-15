package com.FoodFusion.FoodFusionPlatform.rdbm.profile;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostedRecipeRepository extends CrudRepository<PostedRecipe, Long> {
    public PostedRecipe findByName(String name);

    public List<PostedRecipe> findByRecipeCuisine(String recipeCuisine);
}
