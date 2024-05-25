package com.FoodFusion.FoodFusionPlatform.services.profile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.FoodFusion.FoodFusionPlatform.rdbm.profile.Settings;
import com.FoodFusion.FoodFusionPlatform.services.profile.SettingsService;

@SpringBootTest
public class SettingsServiceTest {

    @Autowired
    private SettingsService service;

    @BeforeEach
    public void addSettings() {
        Settings settings = new Settings();
        settings.setUserID(1L);
        service.save(settings);
    }

    @Test
    public void testAddSettings() {
        Settings settings = new Settings();
        settings.setUserID(2L);
        long initialSize = service.list().size();
        service.save(settings);
        long updatedSize = service.list().size();
        // since adding, # of settings should be the 1 more than before
        assertEquals(initialSize + 1, updatedSize);
    }

    @Test
    public void testUpdateSettings() {
        // In order to update, need to find something so will be updating the first one
        List<Settings> settingsList = service.list();
        Settings settings = settingsList.get(0);
        settings.setUserID(settings.getUserID() + 1);

        service.save(settings);
        long updatedSize = service.list().size();
        // since updating, # of settings should be the same
        assertEquals(settingsList.size(), updatedSize);
    }

    @Test
    public void testDeleteSettings() {
        // In order to delete, need to find something to delete so will delete the first one
        List<Settings> settingsList = service.list();
        service.delete(settingsList.get(0).getSettingsID());
        long updatedSize = service.list().size();
        // since removing 1, the list should be 1 less than original list
        assertEquals(settingsList.size() - 1, updatedSize);
    }
}
