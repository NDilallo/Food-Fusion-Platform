package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.Comment;
import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.CommentRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> listAll() {
        log.traceEntry("Enter listAll");
        List<Comment> comments = commentRepository.findAll();
        log.traceExit("Exit listAll", comments);
        return comments;
    }

    public Comment save(Comment comment) {
        log.traceEntry("Enter save", comment);
        Comment savedComment = commentRepository.save(comment);
        log.traceExit("Exit save", savedComment);
        return savedComment;
    }

    public Optional<Comment> getById(long id) {
        log.traceEntry("Enter getById", id);
        Optional<Comment> comment = commentRepository.findById(id);
        log.traceExit("Exit getById", comment);
        return comment;
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        commentRepository.deleteById(id);
        log.traceExit("Exit delete");
    }
}
