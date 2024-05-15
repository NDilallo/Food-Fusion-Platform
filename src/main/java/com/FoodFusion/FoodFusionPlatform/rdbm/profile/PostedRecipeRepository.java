package com.FoodFusion.FoodFusionPlatform.rdbm.Profile;

import org.springframework.data.repository.CrudRepository;

public interface PostedRecipeRepository extends CrudRepository<PostedRecipe, Long> {
    public PostedRecipe findByName(String recipeName);
}
