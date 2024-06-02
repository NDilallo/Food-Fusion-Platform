package com.foodFusion.foodFusionPlatform.controller.upload;

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

import com.foodFusion.foodFusionPlatform.rdbm.upload.Draft;
import com.foodFusion.foodFusionPlatform.services.upload.DraftService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

/**
 * Controller for managing Draft entities.
 * @author Matt Nice
 */
@RestController
@RequestMapping("/api/draft")
@Tag(name = "Draft", description = "All draft recipes")
@Log4j2
public class DraftRestController {

    @Autowired
    private DraftService draftService;

    /**
     * List all Draft instances in the table
     * @return a list of Draft instances 
     */
    @GetMapping
    @Operation(summary = "Returns all the drafts")
    @ApiResponse(responseCode = "200", description = "Valid response", 
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Draft.class))})
    public List<Draft> listAll() {
        return draftService.listAll();
    }

    /**
     * Retrieve a Draft instance with the given id from the table
     * @param id
     * @return ResponseEntity<Draft>
     */
    @GetMapping("/{id}")
    @Operation(summary = "Returns a draft by ID")
    @ApiResponse(responseCode = "200", description = "Valid response", 
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Draft.class))})
    public ResponseEntity<Draft> getById(@PathVariable long id) {
        Optional<Draft> draft = draftService.getById(id);
        return draft.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Save a new Draft instance to the table
     * @param draft
     * @return long id of the saved Draft
     */
    @PostMapping
    @Operation(summary = "Save a new draft and returns the draft ID")
    public ResponseEntity<Long> save(@Valid @RequestBody Draft draft) {
        log.traceEntry("enter save", draft);
        Draft savedDraft = draftService.save(draft);
        log.traceExit("exit save", savedDraft);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDraft.getRecipeId());
    }

    /**
     * Update the contents of a Draft in the table
     * @param id
     * @param draft
     * @return ResponseEntity<Void>
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing draft")
    public ResponseEntity<Void> update(@PathVariable long id, @Valid @RequestBody Draft draft) {
        log.traceEntry("enter update", draft);
        Optional<Draft> existingDraft = draftService.getById(id);
        if (existingDraft.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        draft.setRecipeId(id);
        draftService.save(draft);
        log.traceExit("exit update", draft);
        return ResponseEntity.ok().build();
    }

    /**
     * Delete a Draft instance with the given id from the table
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a draft by ID")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.traceEntry("Enter delete", id);
        if (draftService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        draftService.delete(id);
        log.traceExit("Exit delete");
        return ResponseEntity.noContent().build();
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
