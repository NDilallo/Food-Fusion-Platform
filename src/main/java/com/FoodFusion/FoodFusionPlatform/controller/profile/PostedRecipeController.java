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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.foodFusion.foodFusionPlatform.rdbm.profile.PostedRecipe;
import com.foodFusion.foodFusionPlatform.services.profile.PostedRecipeService;

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
@CrossOrigin("*")
@RestController
@RequestMapping("/api/postedrecipe")
@Tag(name = "PostedRecipe", description = "All posted recipes for a user")
@Log4j2
public class PostedRecipeController {
    @Autowired
    private PostedRecipeService service;

    @PostMapping
    public ResponseEntity<String> submitRecipe(@RequestBody Map<String, String> recipeData) {
        PostedRecipe postedRecipe = new PostedRecipe();
        postedRecipe.setName(recipeData.get("recipeName"));
        postedRecipe.setIngredients(recipeData.get("ingredients"));
        postedRecipe.setSteps(recipeData.get("description"));
        postedRecipe.setRecipeCuisine(recipeData.get("cuisine"));
        postedRecipe.setAvgRating(0); // initial rating

        service.save(postedRecipe);

        return ResponseEntity.ok("recipe received and saved");
    }

    @GetMapping
    @Operation(summary = "Returns all the posted recipes for a user")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=PostedRecipe.class))})
    public List<PostedRecipe> list() {
        return service.list();
    }

    /* 
    @PostMapping
    @Operation(summary = "Save the posted recipe and returns the saved posted recipe's id")
    public long save(@RequestBody PostedRecipe r) {
        log.traceEntry("enter save", r);
        service.save(r);
        log.traceExit("exit save", r);        
        return r.getRecipeId();
    }
*/
    @PostMapping("/validated")
    @Operation(summary = "Save the posted recipe to the user's profile")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody PostedRecipe r) {
        log.traceEntry("enter save", r);
        service.save(r);
        log.traceExit("exit save", r);
        return ResponseEntity.ok("new id is " + r.getRecipeId());
    }

    @DeleteMapping
    @Operation(summary = "Delete the posted recipe")
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
