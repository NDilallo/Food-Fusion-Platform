package com.foodFusion.foodFusionPlatform.controller.homePage;

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
import com.foodFusion.foodFusionPlatform.rdbm.homePage.Restaurant;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.RestaurantRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    private static final String RESTAURANT_URL = "/api/restaurant";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAllRestaurants() throws Exception {
		// when - action
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(RESTAURANT_URL));


		var recordCount = (int) restaurantRepository.count();
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk());
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }

	@Autowired
	private ObjectMapper objectMapper;

    @Test
    public void addRestaurant() throws Exception {
		// given - setup or precondition
		Restaurant r = new Restaurant();
        r.setAddress("123 W Dr");
        r.setCuisine("American");
        r.setName("Burgers!");
		String restaurantAsJson = objectMapper.writeValueAsString(r);

		// when - action
		var request = MockMvcRequestBuilders.post(RESTAURANT_URL);
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(restaurantAsJson);
		ResultActions response = mockMvc.perform(request);

		var jsonResponse = response.andReturn().getResponse().getContentAsString();
		// then - verify the output
		Restaurant updatedR = new ObjectMapper().readValue(jsonResponse, Restaurant.class);

		response.andExpect(MockMvcResultMatchers.status().isOk());
		assertNotEquals(updatedR.getRestaurantId(), r.getRestaurantId());
    }

    @Test
    public void addRestaurantValidationFail() throws Exception {
		// given - setup or precondition
		Restaurant r = new Restaurant();
        r.setAddress("123 W Dr");
        r.setCuisine("American");
        r.setName("Burgers!");
		String restaurantAsJson = objectMapper.writeValueAsString(r);

		// when - action
		var request = MockMvcRequestBuilders.post(RESTAURANT_URL+"/valid");
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(restaurantAsJson);
		ResultActions response = mockMvc.perform(request);

		response.andExpect(MockMvcResultMatchers.status().isBadRequest());
//		response.andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is("must not be blank")));
    }

    @Test
    public void addRestaurantValidationPass() throws Exception {
		// given - setup or precondition
		Restaurant r = new Restaurant();
        r.setAddress("123 W Dr");
        r.setCuisine("American");
        r.setName("Burgers!");
		String restaurantAsJson = objectMapper.writeValueAsString(r);

		// when - action
		var request = MockMvcRequestBuilders.post(RESTAURANT_URL+"/valid");
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(restaurantAsJson);
		ResultActions response = mockMvc.perform(request);

		response.andExpect(MockMvcResultMatchers.status().isOk());
    }



	@Test
    public void removeRestaurant() throws Exception {
		// given - setup or precondition
		long beforeSize = restaurantRepository.count();

		// when - action
		var request = MockMvcRequestBuilders.delete(RESTAURANT_URL+"/1");
		ResultActions response = mockMvc.perform(request);

		long afterSize = restaurantRepository.count();

		response.andExpect(MockMvcResultMatchers.status().isOk());
		assertEquals(beforeSize - 1, afterSize);
    }
}
