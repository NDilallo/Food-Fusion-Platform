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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.FoodFusion.FoodFusionPlatform.rdbm.profile.Profile;
import com.FoodFusion.FoodFusionPlatform.rdbm.profile.ProfileRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {
    private static final String PROFILE_URL = "/api/profile";

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllProfiles() throws Exception {
        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(PROFILE_URL));

        var recordCount = (int) profileRepository.count();
        
        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addProfile() throws Exception {
        // given - setup or precondition
        Profile profile = new Profile();
        profile.setPostedRecipesTableID(1);
        profile.setSettingsTableID(1);
        profile.setFollowingTableID(1);
        profile.setDraftsTableID(1);
        profile.setCommentID(1);
        profile.setNotificationID(1);
        profileRepository.save(profile);
        String profileAsJson = objectMapper.writeValueAsString(profile);

        // when - action
        var request = MockMvcRequestBuilders.post(PROFILE_URL);
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(profileAsJson);
        ResultActions response = mockMvc.perform(request);

        var jsonResponse = response.andReturn().getResponse().getContentAsString();
        // then - verify the output
        Profile updatedProfile = new ObjectMapper().readValue(jsonResponse, Profile.class);

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertNotEquals(updatedProfile.getUserID(), profile.getUserID());
    }

    @Test
    public void addProfileValidationFail() throws Exception {
        // given - setup or precondition
        Profile profile = new Profile();
        profile.setPostedRecipesTableID(1);
        profile.setSettingsTableID(1);
        profile.setFollowingTableID(1);
        profile.setDraftsTableID(1);
        profile.setCommentID(1);
        profile.setNotificationID(1);
        profileRepository.save(profile);
        String profileAsJson = objectMapper.writeValueAsString(profile);

        // when - action
        var request = MockMvcRequestBuilders.post(PROFILE_URL + "/valid");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(profileAsJson);
        ResultActions response = mockMvc.perform(request);

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addProfileValidationPass() throws Exception {
        // given - setup or precondition
        Profile profile = new Profile();
        profile.setPostedRecipesTableID(1);
        profile.setSettingsTableID(1);
        profile.setFollowingTableID(1);
        profile.setDraftsTableID(1);
        profile.setCommentID(1);
        profile.setNotificationID(1);
        profileRepository.save(profile);
        String profileAsJson = objectMapper.writeValueAsString(profile);

        // when - action
        var request = MockMvcRequestBuilders.post(PROFILE_URL + "/valid");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(profileAsJson);
        ResultActions response = mockMvc.perform(request);

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void removeProfile() throws Exception {
        // given - setup or precondition
        long beforeSize = profileRepository.count();

        // when - action
        var request = MockMvcRequestBuilders.delete(PROFILE_URL + "/1");
        ResultActions response = mockMvc.perform(request);

        long afterSize = profileRepository.count();

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(beforeSize - 1, afterSize);
    }
}
