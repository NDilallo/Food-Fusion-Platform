package com.FoodFusion.FoodFusionPlatform.services.profile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.FoodFusion.FoodFusionPlatform.rdbm.profile.Following;
import com.FoodFusion.FoodFusionPlatform.services.profile.FollowingService;

@SpringBootTest
public class FollowingServiceTest {

    @Autowired
    private FollowingService service;

    @BeforeEach
    public void addFollowing() {
        Following following = new Following();
        following.setUserID(1L);
        following.setProfileID(1L);
        service.save(following);
    }

    @Test
    public void testAddFollowing() {
        Following following = new Following();
        following.setUserID(2L);
        following.setProfileID(2L);
        long initialSize = service.list().size();
        service.save(following);
        long updatedSize = service.list().size();
        // since adding, # of followings should be the 1 more than before
        assertEquals(initialSize + 1, updatedSize);
    }

    @Test
    public void testUpdateFollowing() {
        // In order to update, need to find something so will be updating the first one
        List<Following> followings = service.list();
        Following following = followings.get(0);
        following.setUserID(following.getUserID() + 1);

        service.save(following);
        long updatedSize = service.list().size();
        // since updating, # of followings should be the same
        assertEquals(followings.size(), updatedSize);
    }

    @Test
    public void testDeleteFollowing() {
        // In order to delete, need to find something to delete so will delete the first one
        List<Following> followings = service.list();
        service.delete(followings.get(0).getFollowingID());
        long updatedSize = service.list().size();
        // since removing 1, the list should be 1 less than original list
        assertEquals(followings.size() - 1, updatedSize);
    }
}
