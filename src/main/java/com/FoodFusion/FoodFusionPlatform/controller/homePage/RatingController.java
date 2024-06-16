package com.foodFusion.foodFusionPlatform.controller.homePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Rating;
import com.foodFusion.foodFusionPlatform.services.homePage.RatingService;

import lombok.extern.log4j.Log4j2;


/**
 * Controller for managing Rating entities.
 * @author Marisa Ban
 */
@RestController
@RequestMapping("/api/ratings")
@Log4j2
public class RatingController {
    
    @Autowired
    private RatingService service; // Assuming RatingService is adapted for MongoDB operations

    @GetMapping
    public List<Rating> list() {
        return service.list();
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody Rating r) {
        log.traceEntry("Enter save", r);
        Rating savedRating = service.save(r);
        log.traceExit("Exit save", r);
        return ResponseEntity.ok("New rating saved with ID: " + savedRating.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        log.traceEntry("Enter delete", id);
        service.delete(id);
        log.traceExit("Exit delete");
        return ResponseEntity.ok("Rating with ID: " + id + " deleted successfully.");
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
