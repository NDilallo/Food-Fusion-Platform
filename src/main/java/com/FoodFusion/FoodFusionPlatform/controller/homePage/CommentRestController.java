package com.FoodFusion.FoodFusionPlatform.controller.homePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.PostComments;
import com.foodFusion.foodFusionPlatform.services.homePage.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

/**
 * Controller for managing PostComments entities.
 */
@RestController
@RequestMapping("/api/comment")
@Tag(name = "Comment", description = "All comments on posts")
@Log4j2
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    @Operation(summary = "Returns all the comments")
    @ApiResponse(responseCode = "200", description = "Valid response", 
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostComments.class))})
    public List<PostComments> listAll() {
        return commentService.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a comment by ID")
    @ApiResponse(responseCode = "200", description = "Valid response", 
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostComments.class))})
    public ResponseEntity<PostComments> getById(@PathVariable long id) {
        Optional<PostComments> comment = commentService.getById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Save a new comment and returns the comment ID")
    public ResponseEntity<Long> save(@Valid @RequestBody PostComments comment) {
        log.traceEntry("enter save", comment);
        PostComments savedComment = commentService.save(comment);
        log.traceExit("exit save", savedComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment.getId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing comment")
    public ResponseEntity<Void> update(@PathVariable long id, @Valid @RequestBody PostComments comment) {
        log.traceEntry("enter update", comment);
        Optional<PostComments> existingComment = commentService.getById(id);
        if (existingComment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        comment.setId(id);
        commentService.save(comment);
        log.traceExit("exit update", comment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a comment by ID")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.traceEntry("Enter delete", id);
        if (commentService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        commentService.delete(id);
        log.traceExit("Exit delete");
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
