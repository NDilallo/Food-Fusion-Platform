package com.FoodFusion.FoodFusionPlatform.rdbm.profile;

import org.springframework.data.repository.CrudRepository;

public interface PostedRecipeRepository extends CrudRepository<PostedRecipe, Long> {
    public PostedRecipe findByName(String name);
}
