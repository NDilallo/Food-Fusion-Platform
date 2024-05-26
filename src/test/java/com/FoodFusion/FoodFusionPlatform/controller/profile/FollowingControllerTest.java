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

import com.FoodFusion.FoodFusionPlatform.rdbm.profile.Following;
import com.FoodFusion.FoodFusionPlatform.rdbm.profile.FollowingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class FollowingControllerTest {

    private static final String FOLLOWING_URL = "/api/following";

    @Autowired
    private FollowingRepository followingRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllFollowings() throws Exception {
        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(FOLLOWING_URL));

        var recordCount = (int) followingRepository.count();

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }

    @Test
    public void addFollowing() throws Exception {
        // given - setup or precondition
        Following following = new Following();
        following.setUserID(1L);
        following.setProfileID(1L);
        String followingAsJson = objectMapper.writeValueAsString(following);

        // when - action
        var request = MockMvcRequestBuilders.post(FOLLOWING_URL);
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(followingAsJson);
        ResultActions response = mockMvc.perform(request);

        var jsonResponse = response.andReturn().getResponse().getContentAsString();
        // then - verify the output
        Following updatedFollowing = new ObjectMapper().readValue(jsonResponse, Following.class);

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertNotEquals(updatedFollowing.getFollowingID(), following.getFollowingID());
    }

    @Test
    public void addFollowingValidationFail() throws Exception {
        // given - setup or precondition
        Following following = new Following();
        String followingAsJson = objectMapper.writeValueAsString(following);

        // when - action
        var request = MockMvcRequestBuilders.post(FOLLOWING_URL + "/validated");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(followingAsJson);
        ResultActions response = mockMvc.perform(request);

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addFollowingValidationPass() throws Exception {
        // given - setup or precondition
        Following following = new Following();
        following.setUserID(1L);
        following.setProfileID(1L);
        String followingAsJson = objectMapper.writeValueAsString(following);

        // when - action
        var request = MockMvcRequestBuilders.post(FOLLOWING_URL + "/validated");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(followingAsJson);
        ResultActions response = mockMvc.perform(request);

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void removeFollowing() throws Exception {
        // given - setup or precondition
        Following following = new Following();
        following.setUserID(1L);
        following.setProfileID(1L);
        followingRepository.save(following);
        long beforeSize = followingRepository.count();

        // when - action
        var request = MockMvcRequestBuilders.delete(FOLLOWING_URL + "/" + following.getFollowingID());
        ResultActions response = mockMvc.perform(request);

        long afterSize = followingRepository.count();

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(beforeSize - 1, afterSize);
    }
}
