package com.FoodFusion.FoodFusionPlatform.rdbm.upload;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import com.FoodFusion.FoodFusionPlatform.rdbm.profile.Profile;
import lombok.Data;

@Data
@Entity
public class Draft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeId;

    @NotBlank(message = "Recipe name must be set")
    @Size(min = 2, message = "Recipe name must be 2 or more characters")
    private String recipeName;

    @NotBlank(message = "Description/Steps must be set")
    private String description;

    @NotBlank(message = "Ingredients must be set")
    private String ingredients;

    private String draftNotes;

    @NotBlank(message = "Cuisine must be set")
    @Size(min = 2, message = "Cuisine must be 2 or more characters")
    private String cuisine;

   // @NotNull(message = "User ID must be set")
   // @ManyToOne
    //@JoinColumn(name = "user_id", referencedColumnName = "userID", nullable = false)
    //private Profile user; // Foreign key to Profile entity
}
