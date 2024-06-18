package com.foodFusion.foodFusionPlatform.services.homePage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Restaurant;
import com.foodFusion.foodFusionPlatform.services.homePage.RestaurantService;

/**
 * Test out the service
 */
@SpringBootTest
public class RestaurantServiceTest {
    @Autowired
    private RestaurantService service;

    @BeforeEach
    public void addRestaurant() {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setAddress("123 W Dr");
        restaurant1.setCuisine("American");
        restaurant1.setName("Burgers!");
        service.save(restaurant1);
    }

    @Test
    public void testAddRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress("321 W Dr");
        restaurant.setCuisine("Asian");
        restaurant.setName("Noodles!");
        long b4 = service.list().size();
        service.save(restaurant);
        long after = service.list().size();
        // since adding, # of courses should be the 1 more than before
        assertEquals(b4 + 1, after);
    }   
    
    @Test
    public void testUpdateCourse() {
        // In order to update, need to find something so will be updating the first one
        List<Restaurant> restaurants = service.list();
        Restaurant restaurant1 = restaurants.get(0);
        restaurant1.setAddress("877 W Dr");        
        
        service.save(restaurant1);
        long after = service.list().size();
        // since updating, # of courses should be the same
        assertEquals(restaurants.size(), after);
    }

    @Test
    public void testDelete() {
        // In order to delete, need to find something to delete so will delete the first one
        List<Restaurant> restaurants = service.list();
        service.delete(restaurants.get(0).getRestaurantId());
        long after = service.list().size();
        // since removing 1, the list should be 1 less than original list
        assertEquals(restaurants.size() - 1, after);
    }

}
