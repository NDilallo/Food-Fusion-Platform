package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="CommentTmp")
public class PostComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "User link must be set")
    private long userId; // Link to the profile that commented

    @NotBlank(message = "Comment on recipe must be set")
    private String commentOnRecipe; // Comment on recipe
}
