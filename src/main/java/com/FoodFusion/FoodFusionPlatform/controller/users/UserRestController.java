package com.foodFusion.foodFusionPlatform.controller.users;

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

import com.foodFusion.foodFusionPlatform.rdbm.users.User;
import com.foodFusion.foodFusionPlatform.services.users.UserService;

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
@RequestMapping("/api/user")
@Tag(name = "User", description = "All user profiles")
@Log4j2
public class UserRestController {
    @Autowired
    private UserService service;

    @GetMapping
    @Operation(summary = "Returns all the users on the website")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=User.class))})
    public List<User> list() {
        return service.list();
    }

    @PostMapping
    @Operation(summary = "Save the user and returns the user id")
    public long save(@RequestBody User user) {
        log.traceEntry("enter save", user);
        service.save(user);
        log.traceExit("exit save", user);        
        return user.getId();
    }

    @PostMapping("/validated")
    @Operation(summary = "Save the user and returns the user id")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody User user) {
        log.traceEntry("enter save", user);
        service.save(user);
        log.traceExit("exit save", user);
        return ResponseEntity.ok("new id is " + user.getId());
    }

    @DeleteMapping
    @Operation(summary = "Delete the user")
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
