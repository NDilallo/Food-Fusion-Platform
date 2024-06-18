package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Rating;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.RatingRepository;

@SpringBootTest
public class RatingRepositoryTest {

    @Autowired
    private RatingRepository repo;

     /**
     * Test create, read, update, and delete
     */
    @Test
    public void testCrud() {
        Rating rtest = new Rating();
        rtest.setRating(3.0);
        long b4count = repo.count();
        long b4id = rtest.getRatingId();
        repo.save(rtest);
        long aftercount = repo.count();
        long afterid = rtest.getRatingId();

        // there should be 1 more in the database after the save
        assertEquals(b4count + 1, aftercount);

        // original id was 0 but afterwards the id was generated and so should not be equal
        assertNotEquals(b4id, afterid);

        // Scenario of updating cross listing
        // Be sure to find the reference from the database before the update
        Rating updated = repo.findById(afterid);
        updated.setRating(3.5);
        repo.save(updated);

        Rating updatedCheck = repo.findById(afterid);
        assertNotEquals(updatedCheck, rtest);
        assertEquals(3.5, updatedCheck.getRating());

        b4count = repo.count();
        repo.delete(updatedCheck);
        aftercount = repo.count();
        assertEquals(b4count - 1, aftercount);

    }    
}
