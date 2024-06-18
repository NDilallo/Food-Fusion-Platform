package com.foodFusion.foodFusionPlatform.controller.profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.foodFusion.foodFusionPlatform.rdbm.profile.Saved;
import com.foodFusion.foodFusionPlatform.services.profile.SavedService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

/**
 * Controller for managing Saved entities.
 * @author Nick Dilallo
 */
@RestController
@RequestMapping("/api/saved")
@Tag(name = "Saved", description = "All saved posts for user")
@Log4j2
public class SavedController {
    @Autowired
    private SavedService service;

    /**
     * List all the Saved instances in the table
     * @return a list of Saved instances
     */
    @GetMapping
    @Operation(summary = "Returns all the saved posts for a user")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=Saved.class))})
    public List<Saved> list() {
        return service.list();
    }

    /**
     * Save a post to the table
     * @param user
     * @return long id of the saved post
     */
    @PostMapping
    @Operation(summary = "Save the post and returns the saved post's id")
    public long save(@RequestBody Saved user) {
        log.traceEntry("enter save", user);
        service.save(user);
        log.traceExit("exit save", user);        
        return user.getId();
    }

    /**
     * Validate the saved post
     * @param user
     * @return ResponseEntity<String>
     */
    @PostMapping("/validated")
    @Operation(summary = "Save the post to user's profile")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody Saved user) {
        log.traceEntry("enter save", user);
        service.save(user);
        log.traceExit("exit save", user);
        return ResponseEntity.ok("new id is " + user.getId());
    }

    /**
     * Delete a Saved instance from the table
     * @param id
     */
    @DeleteMapping
    @Operation(summary = "Delete the saved post")
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        service.delete(id);
        log.traceExit("Exit delete");
    }
    
    /**
     * @param ex
     * @return Map<String, String>
     */
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
