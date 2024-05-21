package com.FoodFusion.FoodFusionPlatform.controller.searchHistory;

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

import com.FoodFusion.FoodFusionPlatform.rdbm.searchHistory.tmp;
import com.FoodFusion.FoodFusionPlatform.services.searchHistory.SearchHistoryService;
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
@RequestMapping("/api/searchHistory")
@Tag(name = "SearchHistory", description = "All previous searches for user")
@Log4j2
public class SearchHistoryRestController {
    @Autowired
    private SearchHistoryService service;

    @GetMapping
    @Operation(summary = "Returns all the previous searches for a user")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=tmp.class))})
    public List<tmp> list() {
        return service.list();
    }

    @PostMapping
    @Operation(summary = "Save the search and returns the search id")
    public long save(@RequestBody tmp user) {
        log.traceEntry("enter save", user);
        service.save(user);
        log.traceExit("exit save", user);        
        return user.getId();
    }

    @PostMapping("/validated")
    @Operation(summary = "Save the search to history and return the search")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody tmp user) {
        log.traceEntry("enter save", user);
        service.save(user);
        log.traceExit("exit save", user);
        return ResponseEntity.ok("new id is " + user.getId());
    }

    @DeleteMapping
    @Operation(summary = "Delete the search from history")
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
