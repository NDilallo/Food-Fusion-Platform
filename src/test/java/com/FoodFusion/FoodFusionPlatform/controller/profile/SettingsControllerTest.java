package com.FoodFusion.FoodFusionPlatform.controller.profile;

import org.hamcrest.CoreMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.FoodFusion.FoodFusionPlatform.rdbm.profile.Settings;
import com.FoodFusion.FoodFusionPlatform.rdbm.profile.SettingsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class SettingsControllerTest {

    private static final String SETTINGS_URL = "/api/settings";

    @Autowired
    private SettingsRepository settingsRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllSettings() throws Exception {
        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(SETTINGS_URL));

        var recordCount = (int) settingsRepository.count();

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }

    @Test
    public void addSettings() throws Exception {
        // given - setup or precondition
        Settings settings = new Settings();
        settings.setUserID(1L);
        String settingsAsJson = objectMapper.writeValueAsString(settings);

        // when - action
        var request = MockMvcRequestBuilders.post(SETTINGS_URL);
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(settingsAsJson);
        ResultActions response = mockMvc.perform(request);

        var jsonResponse = response.andReturn().getResponse().getContentAsString();
        // then - verify the output
        Settings updatedSettings = new ObjectMapper().readValue(jsonResponse, Settings.class);

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertNotEquals(updatedSettings.getSettingsID(), settings.getSettingsID());
    }

    @Test
    public void addSettingsValidationFail() throws Exception {
        // given - setup or precondition
        Settings settings = new Settings();
        String settingsAsJson = objectMapper.writeValueAsString(settings);

        // when - action
        var request = MockMvcRequestBuilders.post(SETTINGS_URL + "/validated");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(settingsAsJson);
        ResultActions response = mockMvc.perform(request);

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addSettingsValidationPass() throws Exception {
        // given - setup or precondition
        Settings settings = new Settings();
        settings.setUserID(1L);
        String settingsAsJson = objectMapper.writeValueAsString(settings);

        // when - action
        var request = MockMvcRequestBuilders.post(SETTINGS_URL + "/validated");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(settingsAsJson);
        ResultActions response = mockMvc.perform(request);

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void removeSettings() throws Exception {
        // given - setup or precondition
        Settings settings = new Settings();
        settings.setUserID(1L);
        settingsRepository.save(settings);
        long beforeSize = settingsRepository.count();

        // when - action
        var request = MockMvcRequestBuilders.delete(SETTINGS_URL + "/" + settings.getSettingsID());
        ResultActions response = mockMvc.perform(request);

        long afterSize = settingsRepository.count();

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(beforeSize - 1, afterSize);
    }
}
