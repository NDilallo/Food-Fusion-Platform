package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {
    public Rating findById(long ratingId);
}
