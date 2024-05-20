package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import com.FoodFusion.FoodFusionPlatform.rdbm.profile.PostedRecipe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name="Ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ratingId;

    /*
    // link to the profile of the rater (foreign key)
    // one to many relationship -> put this relationship in user class
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private User user
     */

    // foreign key, link to recipe that was rated
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    //@JsonBackReference
    private PostedRecipe recipe;

    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    private double rating;
}
