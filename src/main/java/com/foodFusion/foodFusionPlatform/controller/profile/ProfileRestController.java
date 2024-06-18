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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import lombok.extern.log4j.Log4j2;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/profile")
@Tag(name = "Profile", description = "All user profiles")
@Log4j2
public class ProfileRestController {
    @Autowired
    private ProfileService service;

    @GetMapping
    @Operation(summary = "Returns all the profiles on the website")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=Profile.class))})
    public List<Profile> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns the profile by id")
    public Profile getProfileById(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Save the profile and returns the profile id")
    public long save(@RequestBody Profile profile) {
        log.traceEntry("enter save", profile);
        Profile savedProfile = service.save(profile);
        log.traceExit("exit save", profile);        
        return savedProfile.getUserID();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the profile")
    public ResponseEntity<String> updateProfile(@PathVariable long id, @RequestBody Profile profile) {
        log.traceEntry("enter update", profile);
        Profile existingProfile = service.findById(id);
        if (existingProfile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found");
        }

        existingProfile.setFirstName(profile.getFirstName());
        existingProfile.setLastName(profile.getLastName());
        existingProfile.setCity(profile.getCity());
        existingProfile.setAboutMe(profile.getAboutMe());
        existingProfile.setEmailAddress(profile.getEmailAddress());

        service.save(existingProfile);
        log.traceExit("exit update", profile);
        return ResponseEntity.ok("Profile updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the profile")
    public void delete(@PathVariable long id) {
        log.traceEntry("Enter delete", id);
        service.delete(id);
        log.traceExit("Exit delete");
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
