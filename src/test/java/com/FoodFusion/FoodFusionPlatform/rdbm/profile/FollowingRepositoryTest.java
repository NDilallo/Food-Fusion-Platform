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

    @Test
    public void testCreateFollowing() {
        Following following = new Following();
        following.setUserID(1L);
        following.setProfileID(2L);

        Following savedFollowing = repo.save(following);

        assertNotEquals(0, savedFollowing.getFollowingID());
    }

    @Test
    public void testReadFollowing() {
        Following following = new Following();
        following.setUserID(1L);
        following.setProfileID(2L);

        Following savedFollowing = repo.save(following);
        Following retrievedFollowing = repo.findById(savedFollowing.getFollowingID()).orElse(null);

        assertEquals(savedFollowing, retrievedFollowing);
    }

    @Test
    public void testUpdateFollowing() {
        Following following = new Following();
        following.setUserID(1L);
        following.setProfileID(2L);

        Following savedFollowing = repo.save(following);
        savedFollowing.setUserID(3L);

        Following updatedFollowing = repo.save(savedFollowing);
        assertEquals(3L, updatedFollowing.getUserID());
    }

    @Test
    public void testDeleteFollowing() {
        Following following = new Following();
        following.setUserID(1L);
        following.setProfileID(2L);

        Following savedFollowing = repo.save(following);
        long id = savedFollowing.getFollowingID();
        repo.deleteById(id);

        boolean exists = repo.findById(id).isPresent();
        assertEquals(false, exists);
    }
}
