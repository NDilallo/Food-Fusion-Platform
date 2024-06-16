package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author Marisa Ban
 * This class defines the repository for the nosql Rating table.
 * 
 */
@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    /**
     * Finds the rating in the Ratings table by its id
     * @param ratingId
     * @return Rating
     */
    public Rating findById(long ratingId);
}
