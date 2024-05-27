package com.FoodFusion.FoodFusionPlatform.services.homePage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.CommentRepository;
import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.PostComments;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private PostComments comment;

    @BeforeEach
    void setUp() {
        comment = new PostComments();
        comment.setId(1L);
        comment.setUserId(1L);
        comment.setCommentOnRecipe("This is a test comment.");
    }

    @Test
    void listAll() {
        when(commentRepository.findAll()).thenReturn(Arrays.asList(comment));

        List<PostComments> comments = commentService.listAll();

        assertThat(comments).hasSize(1);
        assertThat(comments.get(0).getCommentOnRecipe()).isEqualTo("This is a test comment.");
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    void save() {
        when(commentRepository.save(any(PostComments.class))).thenReturn(comment);

        PostComments savedComment = commentService.save(comment);

        assertThat(savedComment.getCommentOnRecipe()).isEqualTo("This is a test comment.");
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void getById() {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        Optional<PostComments> foundComment = commentService.getById(1L);

        assertThat(foundComment).isPresent();
        assertThat(foundComment.get().getCommentOnRecipe()).isEqualTo("This is a test comment.");
        verify(commentRepository, times(1)).findById(1L);
    }

    @Test
    void getById_NotFound() {
        when(commentRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<PostComments> foundComment = commentService.getById(1L);

        assertThat(foundComment).isNotPresent();
        verify(commentRepository, times(1)).findById(1L);
    }

    @Test
    void delete() {
        doNothing().when(commentRepository).deleteById(1L);

        commentService.delete(1L);

        verify(commentRepository, times(1)).deleteById(1L);
    }
}
