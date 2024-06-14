package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Dhruvi
 * This class defines the repository for the Chefs table.
 * 
 */
public interface ChefsRepository extends CrudRepository<Chefs, Long> {
    // Chefs findByName(String name);
    // Chefs findSpecialtyChefs(String specialty);
}
