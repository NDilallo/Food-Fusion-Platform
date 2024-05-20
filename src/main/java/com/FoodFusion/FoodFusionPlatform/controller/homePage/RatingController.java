package com.FoodFusion.FoodFusionPlatform.controller.homePage;

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

import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.Rating;
import com.FoodFusion.FoodFusionPlatform.services.RatingService;

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
@RequestMapping("/api/ratings")
@Tag(name = "Rating", description = "All ratings")
@Log4j2
public class RatingController {
    @Autowired
    private RatingService service;

    @GetMapping
    @Operation(summary = "Returns all the ratings for a post")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=Rating.class))})
    public List<Rating> list() {
        return service.list();
    }

    @PostMapping
    @Operation(summary = "Save the rating and returns the saved rating's id")
    public long save(@RequestBody Rating r) {
        log.traceEntry("enter save", r);
        service.save(r);
        log.traceExit("exit save", r);        
        return r.getRatingId();
    }

    @PostMapping("/validated")
    @Operation(summary = "Save the rating to the user's post")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody Rating r) {
        log.traceEntry("enter save", r);
        service.save(r);
        log.traceExit("exit save", r);
        return ResponseEntity.ok("new id is " + r.getRatingId());
    }

    @DeleteMapping
    @Operation(summary = "Delete the rating")
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