package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import com.FoodFusion.FoodFusionPlatform.rdbm.profile.PostedRecipe;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

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
    private PostedRecipe recipe;

    // how to keep rating between 0 and 5?
    private double rating;
}
