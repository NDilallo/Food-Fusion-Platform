package com.foodFusion.foodFusionPlatform.services.profile;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.*;
=======
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
>>>>>>> 3b37e5a4d33637191d5a4dc1f6a0af596eb5f255

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodFusion.foodFusionPlatform.rdbm.profile.Profile;
import com.foodFusion.foodFusionPlatform.rdbm.profile.ProfileRepository;

@SpringBootTest
public class ProfileServiceTest {

    @Autowired
    private ProfileService service;

    @Autowired
    private ProfileRepository repo;

    @BeforeEach
    public void addProfile() {
        Profile profile = new Profile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setCity("Chicago");
        profile.setAboutMe("This is a test profile");
        profile.setEmailAddress("john.doe@example.com");
        service.save(profile);
    }

    @Test
    public void testAddProfile() {
        Profile profile = new Profile();
        profile.setFirstName("Jane");
        profile.setLastName("Smith");
        profile.setCity("New York");
        profile.setAboutMe("Another test profile");
        profile.setEmailAddress("jane.smith@example.com");

        long beforeCount = service.list().size();
        service.save(profile);
        long afterCount = service.list().size();

        assertEquals(beforeCount + 1, afterCount);
    }

    @Test
    public void testUpdateProfile() {
        List<Profile> profiles = service.list();
        Profile profile1 = profiles.get(0);
        profile1.setCity("Los Angeles");

        service.save(profile1);
        long afterCount = service.list().size();

        assertEquals(profiles.size(), afterCount);
        Profile updatedProfile = repo.findById(profile1.getUserID()).orElse(null);
        assertNotNull(updatedProfile);
        assertEquals(updatedProfile.getCity(), "Los Angeles");
    }

    @Test
    public void testDeleteProfile() {
        List<Profile> profiles = service.list();
        long beforeCount = profiles.size();
        service.delete(profiles.get(0).getUserID());
        long afterCount = service.list().size();

        assertEquals(beforeCount - 1, afterCount);
    }
}
