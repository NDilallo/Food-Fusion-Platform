package com.foodFusion.foodFusionPlatform.services.homePage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    private RatingRepository repo;

    /**
     * List all the ratings
     * @return a list of Rating instances
     */
    public List<Rating> list() {
        log.traceEntry("Enter List");
        List<Rating> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);
        return retval;
    }

    /**
     * Save a rating
     * @param rating
     * @return the saved Rating instance
     */
    public Rating save(Rating rating) {
        log.traceEntry("enter save", rating);
        repo.save(rating);
        log.traceExit("Exit list", rating);
        return rating;                
    }

    /**
     * delete a rating with the given id
     * @param id
     */
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

}
