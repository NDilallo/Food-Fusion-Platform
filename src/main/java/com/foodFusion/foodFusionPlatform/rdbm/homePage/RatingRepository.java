package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Marisa Ban
 * This class defines the repository for the Rating table.
 * 
 */
public interface RatingRepository extends CrudRepository<Rating, Long> {
    /**
     * Finds the rating in the Ratings table by its id
     * @param ratingId
     * @return Rating
     */
    public Rating findById(long ratingId);
}
