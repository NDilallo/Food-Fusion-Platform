package com.foodFusion.foodFusionPlatform.services.homePage;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.CommentRepository;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.PostComments;

import lombok.extern.log4j.Log4j2;

/**
 * Defines the services for Comments
 * @author Matt Nice
 */
@Service
@Log4j2
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * List all comments
     * @return a list of PostedComments
     */
    public List<PostComments> listAll() {
        log.traceEntry("Enter listAll");
        List<PostComments> comments = StreamSupport.stream(commentRepository.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit listAll", comments);
        return comments;
    }

    /**
     * Save a comment
     * @param comment
     * @return the saved PostComments instanced
     */
    public PostComments save(PostComments comment) {
        log.traceEntry("Enter save", comment);
        PostComments savedComment = commentRepository.save(comment);
        log.traceExit("Exit save", savedComment);
        return savedComment;
    }

    /**
     * Retrieve a comment with the given id
     * @param id
     * @return the retrieved comment
     */
    public Optional<PostComments> getById(long id) {
        log.traceEntry("Enter getById", id);
        Optional<PostComments> comment = commentRepository.findById(id);
        log.traceExit("Exit getById", comment);
        return comment;
    }

    /**
     * delete a comment with the given id
     * @param id
     */
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        commentRepository.deleteById(id);
        log.traceExit("Exit delete");
    }
}
