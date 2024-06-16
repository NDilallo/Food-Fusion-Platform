package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RatingRepositoryTest {
    @Autowired
    private RatingRepository repo;


    @Test
    public void testAddFind() {
        Rating rating = new Rating();
        rating.setRating(3.0);
        rating.setRecipeId(11111);

        repo.save(rating);

        Optional<Rating> findRating = repo.findById(rating.getId());
        
        assertEquals(rating.getId(), findRating.get().getId());
        
    };
}