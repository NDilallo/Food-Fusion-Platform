package com.foodFusion.foodFusionPlatform.controller.profile;

import static org.junit.jupiter.api.Assertions.*;

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
import com.foodFusion.foodFusionPlatform.rdbm.profile.Profile;
import com.foodFusion.foodFusionPlatform.rdbm.profile.ProfileRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

    private static final String PROFILE_URL = "/api/profile";

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllProfiles() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(PROFILE_URL));

        var recordCount = (int) profileRepository.count();

        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(recordCount));
    }

    @Test
    public void addProfile() throws Exception {
        Profile profile = new Profile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setCity("Chicago");
        profile.setAboutMe("This is a test profile");
        profile.setEmailAddress("john.doe@example.com");

        String profileAsJson = objectMapper.writeValueAsString(profile);

        var request = MockMvcRequestBuilders.post(PROFILE_URL)
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(profileAsJson);
        ResultActions response = mockMvc.perform(request);

        var jsonResponse = response.andReturn().getResponse().getContentAsString();

        Profile savedProfile = objectMapper.readValue(jsonResponse, Profile.class);

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertNotEquals(savedProfile.getUserID(), 0);
    }

    @Test
    public void addProfileValidationFail() throws Exception {
        Profile profile = new Profile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        // Omitting required email field to trigger validation error
        profile.setCity("Chicago");
        profile.setAboutMe("This is a test profile");

        String profileAsJson = objectMapper.writeValueAsString(profile);

        var request = MockMvcRequestBuilders.post(PROFILE_URL + "/validated")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(profileAsJson);
        ResultActions response = mockMvc.perform(request);

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addProfileValidationPass() throws Exception {
        Profile profile = new Profile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setCity("Chicago");
        profile.setAboutMe("This is a test profile");
        profile.setEmailAddress("john.doe@example.com");

        String profileAsJson = objectMapper.writeValueAsString(profile);

        var request = MockMvcRequestBuilders.post(PROFILE_URL + "/validated")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(profileAsJson);
        ResultActions response = mockMvc.perform(request);

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void removeProfile() throws Exception {
        Profile profile = new Profile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setCity("Chicago");
        profile.setAboutMe("This is a test profile");
        profile.setEmailAddress("john.doe@example.com");

        profile = profileRepository.save(profile);
        long id = profile.getUserID();

        var request = MockMvcRequestBuilders.delete(PROFILE_URL + "/" + id);
        ResultActions response = mockMvc.perform(request);

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(profileRepository.findById(id).isPresent(), false);
    }
}
