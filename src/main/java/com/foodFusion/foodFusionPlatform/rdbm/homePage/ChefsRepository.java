package com.foodFusion.foodFusionPlatform.rdbm.homePage;

//import org.hibernate.mapping.List;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Dhruvi
 * This class defines the repository for the Chefs table.
 * 
 */
public interface ChefsRepository extends CrudRepository<Chefs, Long> {
    Chefs findByName(String name);
    // Chefs findSpecialtyChef(String specialty);
    //@SuppressWarnings("null")
    List<Chefs> findAll();  // Make sure this method returns a List<Chef>
}