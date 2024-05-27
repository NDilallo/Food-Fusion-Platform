package com.foodFusion.FoodFusionPlatform.controller.homePage;

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
import com.foodFusion.foodFusionPlatform.rdbm.homePage.Rating;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.RatingRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {
    private static final String RATING_URL = "/api/ratings";

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllRatings() throws Exception {
		// when - action
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(RATING_URL));


		var recordCount = (int) ratingRepository.count();
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk());
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }

	@Autowired
	private ObjectMapper objectMapper;

    @Test
    public void addRating() throws Exception {
		// given - setup or precondition
        Rating rating = new Rating();
        rating.setRating(5.0);
        ratingRepository.save(rating);
		String ratingAsJson = objectMapper.writeValueAsString(rating);

		// when - action
		var request = MockMvcRequestBuilders.post(RATING_URL);
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(ratingAsJson);
		ResultActions response = mockMvc.perform(request);

		var jsonResponse = response.andReturn().getResponse().getContentAsString();
		// then - verify the output
		Rating updatedRating = new ObjectMapper().readValue(jsonResponse, Rating.class);

		response.andExpect(MockMvcResultMatchers.status().isOk());
		assertNotEquals(updatedRating.getRatingId(), rating.getRatingId());
    }

    @Test
    public void addRatingValidationFail() throws Exception {
		// given - setup or precondition
		Rating rating = new Rating();
        rating.setRating(5.0);
        ratingRepository.save(rating);
		String ratingAsJson = objectMapper.writeValueAsString(rating);

		// when - action
		var request = MockMvcRequestBuilders.post(RATING_URL+"/valid");
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(ratingAsJson);
		ResultActions response = mockMvc.perform(request);

		response.andExpect(MockMvcResultMatchers.status().isBadRequest());
//		response.andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is("must not be blank")));
    }

    @Test
    public void addRatingValidationPass() throws Exception {
		// given - setup or precondition
		Rating rating = new Rating();
        rating.setRating(5.0);
        ratingRepository.save(rating);
		String ratingAsJson = objectMapper.writeValueAsString(rating);

		// when - action
		var request = MockMvcRequestBuilders.post(RATING_URL+"/valid");
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(ratingAsJson);
		ResultActions response = mockMvc.perform(request);

		response.andExpect(MockMvcResultMatchers.status().isOk());
    }



	@Test
    public void removeRating() throws Exception {
		// given - setup or precondition
		long beforeSize = ratingRepository.count();

		// when - action
		var request = MockMvcRequestBuilders.delete(RATING_URL+"/1");
		ResultActions response = mockMvc.perform(request);

		long afterSize = ratingRepository.count();

		response.andExpect(MockMvcResultMatchers.status().isOk());
		assertEquals(beforeSize - 1, afterSize);
    }
    
}
