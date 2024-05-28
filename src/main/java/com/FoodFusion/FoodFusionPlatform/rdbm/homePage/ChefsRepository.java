package com.foodFusion.FoodFusionPlatform.rdbm.homePage;

import org.springframework.data.repository.CrudRepository;

public interface ChefsRepository extends CrudRepository<Chefs, Long> {
    // Chefs findByName(String name);
    // Chefs findSpecialtyChefs(String specialty);
}
