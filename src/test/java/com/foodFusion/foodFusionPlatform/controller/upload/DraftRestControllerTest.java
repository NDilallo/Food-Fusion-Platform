package com.foodFusion.foodFusionPlatform.controller.upload;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.foodFusion.foodFusionPlatform.rdbm.upload.Draft;
import com.foodFusion.foodFusionPlatform.services.upload.DraftService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class DraftRestControllerTest {
    private static final String DRAFT_URL = "/api/draft";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DraftService draftService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // Setup any initial configurations or mocks
    }

    @Test
    public void getAllDrafts() throws Exception {
        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(DRAFT_URL));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        // Add more expectations based on your requirements
    }

    @Test
    public void getDraftById() throws Exception {
        // given - setup or precondition
        Draft draft = new Draft();
        draft.setRecipeId(1L);
        draft.setRecipeName("Test Recipe");
        draft.setDescription("Test Description");
        draft.setIngredients("Test Ingredients");
        draft.setCuisine("Test Cuisine");

        Mockito.when(draftService.getById(1L)).thenReturn(Optional.of(draft));

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(DRAFT_URL + "/1"));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.recipeName", is("Test Recipe")));
    }

    @Test
    public void addDraft() throws Exception {
        // given - setup or precondition
        Draft draft = new Draft();
        draft.setRecipeName("Test Recipe");
        draft.setDescription("Test Description");
        draft.setIngredients("Test Ingredients");
        draft.setCuisine("Test Cuisine");

        Draft savedDraft = new Draft();
        savedDraft.setRecipeId(1L);
        savedDraft.setRecipeName("Test Recipe");
        savedDraft.setDescription("Test Description");
        savedDraft.setIngredients("Test Ingredients");
        savedDraft.setCuisine("Test Cuisine");

        Mockito.when(draftService.save(Mockito.any(Draft.class))).thenReturn(savedDraft);

        String draftAsJson = objectMapper.writeValueAsString(draft);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post(DRAFT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(draftAsJson));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", is(1)));
    }

    @Test
    public void updateDraft() throws Exception {
        // given - setup or precondition
        Draft draft = new Draft();
        draft.setRecipeName("Updated Recipe");
        draft.setDescription("Updated Description");
        draft.setIngredients("Updated Ingredients");
        draft.setCuisine("Updated Cuisine");

        Mockito.when(draftService.getById(1L)).thenReturn(Optional.of(draft));
        Mockito.when(draftService.save(Mockito.any(Draft.class))).thenReturn(draft);

        String draftAsJson = objectMapper.writeValueAsString(draft);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put(DRAFT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(draftAsJson));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteDraft() throws Exception {
        // given - setup or precondition
        Draft draft = new Draft();
        draft.setRecipeId(1L);
        draft.setRecipeName("Test Recipe");

        Mockito.when(draftService.getById(1L)).thenReturn(Optional.of(draft));
        Mockito.doNothing().when(draftService).delete(1L);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete(DRAFT_URL + "/1"));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void addDraftValidationFail() throws Exception {
        // given - setup or precondition
        Draft draft = new Draft();
        draft.setDescription("Test Description");

        String draftAsJson = objectMapper.writeValueAsString(draft);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post(DRAFT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(draftAsJson));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.recipeName", is("Recipe name must be set")));
    }
}
