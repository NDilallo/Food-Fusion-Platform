package com.FoodFusion.FoodFusionPlatform.rdbm.profile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * Test code for database
 */
@Data
@Entity
public class PostedRecipes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeId;

    @NotBlank(message = "Recipe name dept must be set")
    @Size(min= 2, message = "Recipe name must be 2 or more characters")
    private String recipeName;

    @NotBlank(message = "Ingredients must be set")
    @Size(min= 2,max = 5, message = "Ingredients must be 2 or more characters")
    private String ingredients;
    
    @NotBlank(message = "Steos dept must be set")
    @Size(min= 2,max = 5, message = "Steps must be 2 or more characters")
    private String steps;

    @NotBlank(message = "Cuisine must be set")
    @Size(min= 2,max = 5, message = "Cuisine must be 2 or more characters")
    private String cuisine;

    @NotBlank(message = "Average rating must be set")
    @Size(min= 0.0,max = 5.0, message = "Average rating must be between 0 and 5")
    private double avgRating;

}