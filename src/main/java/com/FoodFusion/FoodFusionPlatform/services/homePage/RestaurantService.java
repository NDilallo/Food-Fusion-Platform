package com.foodFusion.foodFusionPlatform.services.homePage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Restaurant;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.RestaurantRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RestaurantService {
    @Autowired
    private RestaurantRepository repo;

    public List<Restaurant> list() {
        log.traceEntry("Enter List");
        List<Restaurant> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);
        return retval;
    }

    public Restaurant save(Restaurant restaurant) {
        log.traceEntry("enter save", restaurant);
        repo.save(restaurant);
        log.traceExit("Exit list", restaurant);
        return restaurant;                
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

}
