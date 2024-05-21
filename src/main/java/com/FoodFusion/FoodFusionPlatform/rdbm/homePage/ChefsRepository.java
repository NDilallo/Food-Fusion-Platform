package com.FoodFusion.FoodFusionPlatform.rdbm.HomePage;

import org.springframework.data.repository.CrudRepository;

public interface ChefsRepository extends CrudRepository<Chefs, Long> {
    Chefs findByName(String name);
    Chefs findSpecialtyChefs(String specialty);
}
