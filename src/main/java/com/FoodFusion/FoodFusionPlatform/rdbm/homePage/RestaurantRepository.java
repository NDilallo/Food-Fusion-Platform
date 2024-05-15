package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    public Restaurant findByName(String name);

    public List<Restaurant> findByCuisine(String cuisine);
}
