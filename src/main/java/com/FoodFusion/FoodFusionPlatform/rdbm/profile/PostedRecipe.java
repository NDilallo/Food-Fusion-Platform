package com.FoodFusion.FoodFusionPlatform.rdbm.profile;

import java.util.List;

import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.Rating;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="PostedRecipes")
public class PostedRecipe {
    
    @OneToMany
    private List<Rating> ratings;

    /* unsure if this is necessary
    @ManyToMany
    @ToStringExclude
    private List<Restaurant> restaurants;
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeId;

    @NotBlank(message = "Recipe name dept must be set")
    @Size(min= 2, message = "Recipe name must be 2 or more characters")
    private String recipeName;

    @NotBlank(message = "Ingredients must be set")
    @Size(min= 2, message = "Ingredients must be 2 or more characters")
    private String ingredients;
    
    @NotBlank(message = "Steps dept must be set")
    @Size(min= 2, message = "Steps must be 2 or more characters")
    private String steps;

    @NotBlank(message = "Cuisine must be set")
    @Size(min= 2, message = "Cuisine must be 2 or more characters")
    private String recipeCuisine;

    @NotBlank(message = "Average rating must be set")
    @Size(min= 0,max = 5, message = "Average rating must be between 0 and 5")
    private double avgRating;

}