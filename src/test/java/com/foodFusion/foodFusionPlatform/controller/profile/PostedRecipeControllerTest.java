package com.foodFusion.foodFusionPlatform.controller.profile;

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
import com.foodFusion.foodFusionPlatform.rdbm.profile.PostedRecipe;
import com.foodFusion.foodFusionPlatform.rdbm.profile.PostedRecipeRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PostedRecipeControllerTest {
     private static final String RECIPE_URL = "/api/postedrecipe";

    @Autowired
    private PostedRecipeRepository recipeRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAllPostedRecipes() throws Exception {
		// when - action
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(RECIPE_URL));


		var recordCount = (int) recipeRepository.count();
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk());
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }

	@Autowired
	private ObjectMapper objectMapper;

    @Test
    public void addPostedRecipe() throws Exception {
		// given - setup or precondition
		PostedRecipe r = new PostedRecipe();
        r.setAvgRating(3.5);
        r.setIngredients("rice, veggies, soy sauce");
        r.setName("Stir fry");
        r.setRecipeCuisine("Asian");
        r.setSteps("make rice, cook veggies, combine");
		String recipeAsJson = objectMapper.writeValueAsString(r);

		// when - action
		var request = MockMvcRequestBuilders.post(RECIPE_URL);
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(recipeAsJson);
		ResultActions response = mockMvc.perform(request);

		var jsonResponse = response.andReturn().getResponse().getContentAsString();
		// then - verify the output
		PostedRecipe updatedR = new ObjectMapper().readValue(jsonResponse, PostedRecipe.class);

		response.andExpect(MockMvcResultMatchers.status().isOk());
		assertNotEquals(updatedR.getRecipeId(), r.getRecipeId());
    }

    @Test
    public void addPostedRecipeValidationFail() throws Exception {
		// given - setup or precondition
		PostedRecipe r = new PostedRecipe();
        r.setAvgRating(3.5);
        r.setIngredients("rice, veggies, soy sauce");
        r.setName("Stir fry");
        r.setRecipeCuisine("Asian");
        r.setSteps("make rice, cook veggies, combine");
		String recipeAsJson = objectMapper.writeValueAsString(r);

		// when - action
		var request = MockMvcRequestBuilders.post(RECIPE_URL+"/valid");
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(recipeAsJson);
		ResultActions response = mockMvc.perform(request);

		response.andExpect(MockMvcResultMatchers.status().isBadRequest());
//		response.andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is("must not be blank")));
    }

    @Test
    public void addPostedRecipeValidationPass() throws Exception {
		// given - setup or precondition
		PostedRecipe r = new PostedRecipe();
        r.setAvgRating(3.5);
        r.setIngredients("rice, veggies, soy sauce");
        r.setName("Stir fry");
        r.setRecipeCuisine("Asian");
        r.setSteps("make rice, cook veggies, combine");
		String recipeAsJson = objectMapper.writeValueAsString(r);

		// when - action
		var request = MockMvcRequestBuilders.post(RECIPE_URL+"/valid");
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(recipeAsJson);
		ResultActions response = mockMvc.perform(request);

		response.andExpect(MockMvcResultMatchers.status().isOk());
    }



	@Test
    public void removePostedRecipe() throws Exception {
		// given - setup or precondition
		long beforeSize = recipeRepository.count();

		// when - action
		var request = MockMvcRequestBuilders.delete(RECIPE_URL+"/1");
		ResultActions response = mockMvc.perform(request);

		long afterSize = recipeRepository.count();

		response.andExpect(MockMvcResultMatchers.status().isOk());
		assertEquals(beforeSize - 1, afterSize);
    }
}
