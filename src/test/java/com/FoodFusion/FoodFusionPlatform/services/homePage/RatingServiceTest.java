package com.foodFusion.foodFusionPlatform.services.homePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Rating;
import com.foodFusion.foodFusionPlatform.services.homePage.RatingService;
/**
 * Test out the service
 */
@SpringBootTest
public class RatingServiceTest {
    @Autowired
    private RatingService service;

    @BeforeEach
    public void addRating() {
        Rating rating = new Rating();
        rating.setRating(5.0);
        service.save(rating);
    }

    @Test
    public void testAddRating() {
        Rating rating = new Rating();
        rating.setRating(3.0);
        long b4 = service.list().size();
        service.save(rating);
        long after = service.list().size();
        // since adding, # of courses should be the 1 more than before
        assertEquals(b4 + 1, after);
    }   
    
    @Test
    public void testUpdateCourse() {
        // In order to update, need to find something so will be updating the first one
        List<Rating> ratings = service.list();
        Rating rating1 = ratings.get(0);
        rating1.setRating(rating1.getRating() + 1.0);        
        
        service.save(rating1);
        long after = service.list().size();
        // since updating, # of courses should be the same
        assertEquals(ratings.size(), after);
    }

    @Test
    public void testDelete() {
        // In order to delete, need to find something to delete so will delete the first one
        List<Rating> ratings = service.list();
        service.delete(ratings.get(0).getRatingId());
        long after = service.list().size();
        // since removing 1, the list should be 1 less than original list
        assertEquals(ratings.size() - 1, after);
    }

}
