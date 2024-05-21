package com.FoodFusion.FoodFusionPlatform.controller.homePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.Notification;
import com.FoodFusion.FoodFusionPlatform.services.homePage.NotificationServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

/**
 * Controller for managing Notification entities.
 */
@RestController
@RequestMapping("/api/notification")
@Tag(name = "Notification", description = "All notifications")
@Log4j2
public class NotificationRestController {

    @Autowired
    private NotificationServices notificationServices;

    @GetMapping
    @Operation(summary = "Returns all the notifications")
    @ApiResponse(responseCode = "200", description = "Valid response", 
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Notification.class))})
    public List<Notification> listAll() {
        return notificationServices.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a notification by ID")
    @ApiResponse(responseCode = "200", description = "Valid response", 
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Notification.class))})
    public ResponseEntity<Notification> getById(@PathVariable long id) {
        Optional<Notification> notification = notificationServices.getById(id);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Save a new notification and returns the notification ID")
    public ResponseEntity<Long> save(@Valid @RequestBody Notification notification) {
        log.traceEntry("enter save", notification);
        Notification savedNotification = notificationServices.save(notification);
        log.traceExit("exit save", savedNotification);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotification.getId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing notification")
    public ResponseEntity<Void> update(@PathVariable long id, @Valid @RequestBody Notification notification) {
        log.traceEntry("enter update", notification);
        Optional<Notification> existingNotification = notificationServices.getById(id);
        if (existingNotification.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        notification.setId(id);
        notificationServices.save(notification);
        log.traceExit("exit update", notification);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a notification by ID")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.traceEntry("Enter delete", id);
        if (notificationServices.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        notificationServices.delete(id);
        log.traceExit("Exit delete");
        return ResponseEntity.noContent().build();
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
