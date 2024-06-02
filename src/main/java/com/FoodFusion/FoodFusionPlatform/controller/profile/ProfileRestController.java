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

import com.foodFusion.foodFusionPlatform.rdbm.profile.Profile;
import com.foodFusion.foodFusionPlatform.services.profile.ProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

/**
 * Controller for managing Profile entities.
 * @author Fritz Nastor
 */
@RestController
@RequestMapping("/api/profile")
@Tag(name = "Profile", description = "All user profiles")
@Log4j2
public class ProfileRestController {
    @Autowired
    private ProfileService service;

    /**
     * Lists all the Profile instances in the table
     * @return a list of Profile instances
     */
    @GetMapping("/api/profile")
    @Operation(summary = "Returns all the profiles on the website")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=Profile.class))})
    public List<Profile> list() {
        return service.list();
    }

    /**
     * Saves a new Profile to the table
     * @param profile
     * @return long id of the saved Profile
     */
    @PostMapping("/api/profile")
    @Operation(summary = "Save the profile and returns the profile id")
    public long save(@RequestBody Profile profile) {
        log.traceEntry("enter save", profile);
        service.save(profile);
        log.traceExit("exit save", profile);        
        return profile.getUserID();
    }

    /**
     * Validate the saved Profile
     * @param profile
     * @return ResponseEntity<String>
     */
    @PostMapping("/validated")
    @Operation(summary = "Save the profile and returns the profile id")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody Profile profile) {
        log.traceEntry("enter save", profile);
        service.save(profile);
        log.traceExit("exit save", profile);
        return ResponseEntity.ok("new id is " + profile.getUserID());
    }

    /**
     * Delete a Profile with the given id from the table
     * @param id
     */
    @DeleteMapping("/api/pofile/{id}")
    @Operation(summary = "Delete the profile")
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
