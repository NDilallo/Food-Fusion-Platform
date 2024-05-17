package com.FoodFusion.FoodFusionPlatform.rdbm.HomePage;

import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    public Restaurant findByName(String name);
}
