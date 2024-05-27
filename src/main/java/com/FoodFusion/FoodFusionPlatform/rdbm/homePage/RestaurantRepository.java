package com.foodFusion.FoodFusionPlatform.rdbm.homePage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    public List<Restaurant> findByName(String name);

    public Restaurant findByRestaurantId(long restaurantId);

    public Restaurant findByAddress(String address);

    public List<Restaurant> findByCuisine(String cuisine);
}
