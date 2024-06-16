package com.foodFusion.foodFusionPlatform.services.homePage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Rating;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.RatingRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Defines the services for the Rating table
 * @author Marisa Ban
 */

@Service
@Log4j2
public class RatingService {
    
    @Autowired
    private RatingRepository repo; // Assuming RatingRepository extends MongoRepository<Rating, String>

    /**
     * List all the ratings
     * @return a list of Rating instances
     */
    public List<Rating> list() {
        log.traceEntry("Enter list");
        List<Rating> retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return retval;
    }

    /**
     * Save a rating
     * @param rating the Rating object to save
     * @return the saved Rating instance
     */
    public Rating save(Rating rating) {
        log.traceEntry("Enter save", rating);
        Rating savedRating = repo.save(rating);
        log.traceExit("Exit save", savedRating);
        return savedRating;
    }

    /**
     * Delete a rating by its ID
     * @param id the ID of the rating to delete
     */
    public void delete(String id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

    /**
     * Find a rating by its ID
     * @param id the ID of the rating to find
     * @return the Optional containing the Rating if found, empty otherwise
     */
    public Optional<Rating> findById(String id) {
        log.traceEntry("Enter findById", id);
        Optional<Rating> rating = repo.findById(id);
        log.traceExit("Exit findById", rating);
        return rating;
    }

}
