package com.foodFusion.foodFusionPlatform.controller.profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.foodFusion.foodFusionPlatform.rdbm.profile.Profile;
import com.foodFusion.foodFusionPlatform.rdbm.profile.Settings;
import com.foodFusion.foodFusionPlatform.services.profile.SettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

/*
 * Documented controller using OpenAPI
 */
@RestController
@RequestMapping("/api/settings")
@Tag(name = "Settings", description = "User settings management")
@Log4j2
@CrossOrigin("*")
public class SettingsController {
    @Autowired
    private SettingsService service;

    @GetMapping
    @Operation(summary = "Returns all the settings on the website")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=Settings.class))})
    public List<Settings> list() {
        return service.list();
    }




    @PostMapping
    @Operation(summary = "Save the settings and returns the settings id")
    public long save(@RequestBody Settings settings) {
        log.traceEntry("enter save", settings);
        service.save(settings);
        log.traceExit("exit save", settings);        
        return settings.getSettingsID();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the settings with the given id")
    public ResponseEntity<String> update(@PathVariable long id, @Valid @RequestBody Settings settings) {
        log.traceEntry("Enter update", id, settings);
        Settings updatedSettings = service.update(id, settings);
        if (updatedSettings != null) {
            log.traceExit("Exit update", updatedSettings);
            return ResponseEntity.ok("Settings with id " + id + " has been updated");
        } else {
            log.traceExit("Exit update - not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Settings with id " + id + " not found");
        }
    }


    @PostMapping("/validated")
    @Operation(summary = "Save the settings and returns the settings id")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody Settings settings) {
        log.traceEntry("enter save", settings);
        service.save(settings);
        log.traceExit("exit save", settings);
        return ResponseEntity.ok("new id is " + settings.getSettingsID());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get the settings with the given id")
    public ResponseEntity<Settings> getById(@PathVariable long id) {
        log.traceEntry("Enter getById", id);
        Settings settings = service.getById(id);
        if (settings != null) {
            log.traceExit("Exit getById", settings);
            return ResponseEntity.ok(settings);
        } else {
            log.traceExit("Exit getById - not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the settings")
    public void delete(long id) {
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
