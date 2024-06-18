package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Marisa Ban
 * This class defines the repository for the Restaurant table.
 * 
 */
public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    /**
     * Finds the restuarant(s) with the given name in the Restaurants table
     * @param name
     * @return list of Restaurants
     */
    public List<Restaurant> findByName(String name);

    /**
     * Finds the restaurant given the restaurant id
     * @param restaurantId
     * @return a Restaurant instance
     */
    public Restaurant findByRestaurantId(long restaurantId);

    /**
     * Finds the restaurant with the given address
     * @param address
     * @return a Restaurant instance
     */
    public Restaurant findByAddress(String address);

    /**
     * Finds the restaurant(s) with the given cuisine types
     * @param cuisine
     * @return a list of Restaurants
     */
    public List<Restaurant> findByCuisine(String cuisine);
}
