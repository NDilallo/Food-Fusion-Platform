package com.FoodFusion.FoodFusionPlatform.rdbm.HomePage;

import org.springframework.data.repository.CrudRepository;

public interface ChefsRepository<Chefs> extends CrudRepository<Chefs, Long> {
    public Chefs findByName(String name);
}

