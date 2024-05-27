package com.foodFusion.foodFusionPlatform.rdbm.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FollowingRepositoryTest {

    @Autowired
    private FollowingRepository repo;

    /**
     * Test create, read, update, and delete
     */
    @Test
    public void testCrud() {
        Following following = new Following();
        following.setUserID(1L);
        following.setProfileID(1L);
        long initialCount = repo.count();
        long initialId = following.getFollowingID();
        repo.save(following);
        long updatedCount = repo.count();
        long updatedId = following.getFollowingID();

        // there should be 1 more in the database after the save
        assertEquals(initialCount + 1, updatedCount);

        // original id was 0 but afterwards the id was generated and so should not be equal
        assertNotEquals(initialId, updatedId);

        // Scenario of updating
        Following updatedFollowing = repo.findById(updatedId).orElse(null);
        updatedFollowing.setUserID(2L);
        repo.save(updatedFollowing);

        Following updatedCheck = repo.findById(updatedId).orElse(null);
        assertNotEquals(updatedCheck, following);
        assertEquals(2L, updatedCheck.getUserID());

        initialCount = repo.count();
        repo.delete(updatedCheck);
        updatedCount = repo.count();
        assertEquals(initialCount - 1, updatedCount);
    }
}
