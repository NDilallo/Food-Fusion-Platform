package com.foodFusion.foodFusionPlatform.services.profile;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodFusion.foodFusionPlatform.rdbm.profile.Profile;

@SpringBootTest
public class ProfileServiceTest {
    @Autowired
    private ProfileService service;

    @BeforeEach
    public void addProfile() {
        Profile profile = new Profile();
        profile.setPostedRecipesTableID(1);
        profile.setSettingsTableID(1);
        profile.setFollowingTableID(1);
        profile.setDraftsTableID(1);
        profile.setCommentID(1);
        profile.setNotificationID(1);
        service.save(profile);
    }

    @Test
    public void testAddProfile() {
        Profile profile = new Profile();
        profile.setPostedRecipesTableID(2);
        profile.setSettingsTableID(2);
        profile.setFollowingTableID(2);
        profile.setDraftsTableID(2);
        profile.setCommentID(2);
        profile.setNotificationID(2);
        long b4 = service.list().size();
        service.save(profile);
        long after = service.list().size();
        // since adding, # of profiles should be the 1 more than before
        assertEquals(b4 + 1, after);
    }   
    
    @Test
    public void testUpdateProfile() {
        // In order to update, need to find something so will be updating the first one
        List<Profile> profiles = service.list();
        Profile profile1 = profiles.get(0);
        profile1.setCommentID(profile1.getCommentID() + 1);        
        
        service.save(profile1);
        long after = service.list().size();
        // since updating, # of profiles should be the same
        assertEquals(profiles.size(), after);
    }

    @Test
    public void testDelete() {
        // In order to delete, need to find something to delete so will delete the first one
        List<Profile> profiles = service.list();
        service.delete(profiles.get(0).getUserID());
        long after = service.list().size();
        // since removing 1, the list should be 1 less than original list
        assertEquals(profiles.size() - 1, after);
    }

}
