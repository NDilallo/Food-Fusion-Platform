package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "User link must be set")
    private long userId; // Link to the profile that commented

    @NotBlank(message = "Comment on recipe must be set")
    private String commentOnRecipe; // Comment on recipe
}
