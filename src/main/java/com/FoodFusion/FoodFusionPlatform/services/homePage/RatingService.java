package com.foodFusion.FoodFusionPlatform.services.homePage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Rating;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.RatingRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RatingService {
    @Autowired
    private RatingRepository repo;

    public List<Rating> list() {
        log.traceEntry("Enter List");
        List<Rating> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);
        return retval;
    }

    public Rating save(Rating rating) {
        log.traceEntry("enter save", rating);
        repo.save(rating);
        log.traceExit("Exit list", rating);
        return rating;                
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

}
