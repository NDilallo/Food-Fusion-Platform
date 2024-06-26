package com.foodFusion.foodFusionPlatform.controller.homePage;

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

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Restaurant;
import com.foodFusion.foodFusionPlatform.services.homePage.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

/**
 * Controller for managing Restaurant entities.
 * @author Marisa Ban
 */
@RestController
@RequestMapping("/api/restaurant")
@Tag(name = "Restaurant", description = "All restaurants")
@Log4j2
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    /**
     * List all Restaurant instances from the table 
     * @return a list of Restaurant instances
     */
    @GetMapping
    @Operation(summary = "Returns all the restaurants in the system")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=Restaurant.class))})
    public List<Restaurant> list() {
        return service.list();
    }

    /**
     * Save a new Restaurant to the table
     * @param r
     * @return long id of saved Restaurant
     */
    @PostMapping
    @Operation(summary = "Save the restaurant and returns the saved restaurant's id")
    public long save(@RequestBody Restaurant r) {
        log.traceEntry("enter save", r);
        service.save(r);
        log.traceExit("exit save", r);        
        return r.getRestaurantId();
    }

    /**
     * Validate the save of a Restaurant to the table
     * @param r
     * @return ResponseEntity<String> 
     */
    @PostMapping("/validated")
    @Operation(summary = "Save the restaurant")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody Restaurant r) {
        log.traceEntry("enter save", r);
        service.save(r);
        log.traceExit("exit save", r);
        return ResponseEntity.ok("new id is " + r.getRestaurantId());
    }

    /**
     * Delete a Restaurant with the given id from the table
     * @param id
     */
    @DeleteMapping
    @Operation(summary = "Delete the restaurant")
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
