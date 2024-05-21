package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table; // Correct import for @Table annotation
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="Comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "User link must be set")
    private long userId; // Link to the profile that commented

    @NotBlank(message = "Comment on recipe must be set")
    private String commentOnRecipe; // Comment on recipe
}
