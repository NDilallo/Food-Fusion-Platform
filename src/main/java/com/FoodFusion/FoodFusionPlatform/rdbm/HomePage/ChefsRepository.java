package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import org.springframework.data.repository.CrudRepository;

public interface ChefsRepository extends CrudRepository<Chefs, Long> {
    public Chefs findByName(String name);
}

