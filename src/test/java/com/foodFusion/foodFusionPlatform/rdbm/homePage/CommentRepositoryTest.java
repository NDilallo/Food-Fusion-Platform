package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testSaveAndFindById() {
        // Create a new comment
        PostComments comment = new PostComments();
        comment.setUserId(1L);
        comment.setCommentOnRecipe("This is a great recipe!");

        // Save the comment
        PostComments savedComment = commentRepository.save(comment);

        // Find the comment by ID
        Optional<PostComments> foundComment = commentRepository.findById(savedComment.getId());

        // Assert the comment is found and the details are correct
        assertThat(foundComment).isPresent();
        assertThat(foundComment.get().getUserId()).isEqualTo(1L);
        assertThat(foundComment.get().getCommentOnRecipe()).isEqualTo("This is a great recipe!");
    }

    @Test
    public void testFindAll() {
        // Create and save multiple comments
        PostComments comment1 = new PostComments();
        comment1.setUserId(1L);
        comment1.setCommentOnRecipe("First comment!");

        PostComments comment2 = new PostComments();
        comment2.setUserId(2L);
        comment2.setCommentOnRecipe("Second comment!");

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // Find all comments
        Iterable<PostComments> comments = commentRepository.findAll();

        // Assert that the comments are found
        assertThat(comments).hasSize(2);
    }

    @Test
    public void testDeleteById() {
        // Create and save a comment
        PostComments comment = new PostComments();
        comment.setUserId(1L);
        comment.setCommentOnRecipe("This is a comment to be deleted");

        PostComments savedComment = commentRepository.save(comment);

        // Delete the comment by ID
        commentRepository.deleteById(savedComment.getId());

        // Assert the comment is deleted
        Optional<PostComments> foundComment = commentRepository.findById(savedComment.getId());
        assertThat(foundComment).isNotPresent();
    }
}
