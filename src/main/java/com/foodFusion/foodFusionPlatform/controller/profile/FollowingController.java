package com.foodFusion.foodFusionPlatform.controller.profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.foodFusion.foodFusionPlatform.rdbm.profile.Following;
import com.foodFusion.foodFusionPlatform.services.profile.FollowingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;


//done
@CrossOrigin("*")
@RestController
@RequestMapping("/api/following")
@Tag(name = "Following", description = "All user followings")
@Log4j2
public class FollowingController {
    @Autowired
    private FollowingService service;

    @GetMapping
    @Operation(summary = "Returns all the followings for a user")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=Following.class))})
    public List<Following> list(@RequestParam long userId) {
        return service.listByUserId(userId);
    }

    @PostMapping
    @Operation(summary = "Save the following and returns the following id")
    public long save(@RequestBody Following following) {
        log.traceEntry("enter save", following);
        service.save(following);
        log.traceExit("exit save", following);
        return following.getFollowingID();
    }

    @PostMapping("/validated")
    @Operation(summary = "Save the following and returns the following id")
    public ResponseEntity<String> validatedSave(@RequestBody Following following) {
        log.traceEntry("enter save", following);
        service.save(following);
        log.traceExit("exit save", following);
        return ResponseEntity.ok("new id is " + following.getFollowingID());
    }

    @DeleteMapping
    @Operation(summary = "Delete the following")
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
