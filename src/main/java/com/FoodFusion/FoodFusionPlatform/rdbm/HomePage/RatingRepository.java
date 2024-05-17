package com.FoodFusion.FoodFusionPlatform.rdbm.HomePage;

import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {
    public Rating findById(long ratingId);
}
