package com.foodFusion.foodFusionPlatform.rdbm.profile;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.*;
=======
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
>>>>>>> 3b37e5a4d33637191d5a4dc1f6a0af596eb5f255

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository repo;

    @Test
    public void testCrud() {
        Profile profile = new Profile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setCity("Chicago");
        profile.setAboutMe("This is a test profile");
        profile.setEmailAddress("john.doe@example.com");

        long beforeCount = repo.count();
        long beforeId = profile.getUserID();

        repo.save(profile);

        long afterCount = repo.count();
        long afterId = profile.getUserID();

        assertEquals(beforeCount + 1, afterCount);
        assertNotEquals(beforeId, afterId);

        Profile updatedProfile = repo.findById(afterId).orElse(null);
        updatedProfile.setCity("New York");
        repo.save(updatedProfile);

        Profile updatedCheck = repo.findById(afterId).orElse(null);
        assertNotEquals(updatedCheck.getCity(), profile.getCity());
        assertEquals(updatedCheck.getCity(), "New York");

        beforeCount = repo.count();
        repo.delete(updatedCheck);
        afterCount = repo.count();
        assertEquals(beforeCount - 1, afterCount);
    }
}
