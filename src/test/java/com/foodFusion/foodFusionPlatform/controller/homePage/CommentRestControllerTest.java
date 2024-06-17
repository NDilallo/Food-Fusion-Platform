package com.foodFusion.foodFusionPlatform.controller.homePage;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
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

import com.foodFusion.foodFusionPlatform.rdbm.homePage.PostComments;
import com.foodFusion.foodFusionPlatform.services.homePage.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    private PostComments comment;

    @BeforeEach
    public void setup() {
        comment = new PostComments();
        comment.setId(1L);
        comment.setUserId(1L);
        comment.setCommentOnRecipe("This is a test comment");
    }

    @Test
    public void listAll() throws Exception {
        when(commentService.listAll()).thenReturn(Collections.singletonList(comment));

        mockMvc.perform(get("/api/comment"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].commentOnRecipe", is("This is a test comment")));
    }

    @Test
    public void getById() throws Exception {
        when(commentService.getById(1L)).thenReturn(Optional.of(comment));

        mockMvc.perform(get("/api/comment/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.commentOnRecipe", is("This is a test comment")));
    }

    @Test
    public void save() throws Exception {
        when(commentService.save(Mockito.any(PostComments.class))).thenReturn(comment);

        String commentJson = objectMapper.writeValueAsString(comment);

        mockMvc.perform(post("/api/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentJson))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", is(1)));
    }

    @Test
    public void update() throws Exception {
        when(commentService.getById(1L)).thenReturn(Optional.of(comment));
        when(commentService.save(Mockito.any(PostComments.class))).thenReturn(comment);

        String commentJson = objectMapper.writeValueAsString(comment);

        mockMvc.perform(put("/api/comment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentJson))
            .andExpect(status().isOk());
    }

   
@Test
public void deleteComment() throws Exception {
    when(commentService.getById(1L)).thenReturn(Optional.of(comment));
    Mockito.doNothing().when(commentService).delete(1L);

    mockMvc.perform(delete("/api/comment/1"))
        .andExpect(status().isNoContent());
}

    @Test
    public void saveValidationError() throws Exception {
        PostComments invalidComment = new PostComments();
        invalidComment.setUserId(1L);  // Missing commentOnRecipe
        String invalidCommentJson = objectMapper.writeValueAsString(invalidComment);

        mockMvc.perform(post("/api/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidCommentJson))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.commentOnRecipe", is("Comment on recipe must be set")));
    }
}
    