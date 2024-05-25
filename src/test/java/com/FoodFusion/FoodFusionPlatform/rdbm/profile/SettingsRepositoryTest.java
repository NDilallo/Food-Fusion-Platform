package com.FoodFusion.FoodFusionPlatform.rdbm.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SettingsRepositoryTest {

    @Autowired
    private SettingsRepository repo;

    /**
     * Test create, read, update, and delete
     */
    @Test
    public void testCrud() {
        Settings settings = new Settings();
        settings.setUserID(1L);
        long initialCount = repo.count();
        long initialId = settings.getSettingsID();
        repo.save(settings);
        long updatedCount = repo.count();
        long updatedId = settings.getSettingsID();

        // there should be 1 more in the database after the save
        assertEquals(initialCount + 1, updatedCount);

        // original id was 0 but afterwards the id was generated and so should not be equal
        assertNotEquals(initialId, updatedId);

        // Scenario of updating
        Settings updatedSettings = repo.findById(updatedId).orElse(null);
        updatedSettings.setUserID(2L);
        repo.save(updatedSettings);

        Settings updatedCheck = repo.findById(updatedId).orElse(null);
        assertNotEquals(updatedCheck, settings);
        assertEquals(2L, updatedCheck.getUserID());

        initialCount = repo.count();
        repo.delete(updatedCheck);
        updatedCount = repo.count();
        assertEquals(initialCount - 1, updatedCount);
    }
}
