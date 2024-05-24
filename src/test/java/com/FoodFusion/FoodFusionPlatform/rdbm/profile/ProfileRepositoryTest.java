package com.FoodFusion.FoodFusionPlatform.rdbm.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodFusion.foodFusionPlatform.rdbm.profile.Profile;
import com.foodFusion.foodFusionPlatform.rdbm.profile.ProfileRepository;

@SpringBootTest
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository repo;

    /**
     * Test create, read, update, and delete
     */
    @Test
    public void testCrud() {
        Profile ptest = new Profile();
        ptest.setPostedRecipesTableID(1);
        ptest.setSettingsTableID(1);
        ptest.setFollowingTableID(1);
        ptest.setDraftsTableID(1);
        ptest.setCommentID(1);
        ptest.setNotificationID(1);

        long b4count = repo.count();
        long b4id = ptest.getUserID();
        repo.save(ptest);
        long aftercount = repo.count();
        long afterid = ptest.getUserID();

        // there should be 1 more in the database after the save
        assertEquals(b4count + 1, aftercount);

        // original id was 0 but afterwards the id was generated and so should not be equal
        assertNotEquals(b4id, afterid);

        // Scenario of updating profile
        Profile updated = repo.findById(afterid).orElse(null);
        updated.setCommentID(2);
        repo.save(updated);

        Profile updatedCheck = repo.findById(afterid).orElse(null);
        assertNotEquals(updatedCheck, ptest);
        assertEquals(2, updatedCheck.getCommentID());

        b4count = repo.count();
        repo.delete(updatedCheck);
        aftercount = repo.count();
        assertEquals(b4count - 1, aftercount);
    }
}
