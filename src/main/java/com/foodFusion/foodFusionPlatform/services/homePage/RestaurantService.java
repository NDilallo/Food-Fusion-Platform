package com.foodFusion.foodFusionPlatform.services.homePage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Restaurant;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.RestaurantRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Defines the services for the Restaurant table
 * @author Marisa Ban
 */
@Service
@Log4j2
public class RestaurantService {
    @Autowired
    private RestaurantRepository repo;

    /**
     * list all the restaurants
     * @return a list of Restaurant instances
     */
    public List<Restaurant> list() {
        log.traceEntry("Enter List");
        List<Restaurant> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);
        return retval;
    }

    /**
     * save a restaurant
     * @param restaurant
     * @return the saved restaurant instance
     */
    public Restaurant save(Restaurant restaurant) {
        log.traceEntry("enter save", restaurant);
        repo.save(restaurant);
        log.traceExit("Exit list", restaurant);
        return restaurant;                
    }

    /**
     * delete a restaurant with the given
     * @param id
     */
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

}
