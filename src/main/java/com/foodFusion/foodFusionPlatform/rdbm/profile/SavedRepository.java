package com.foodFusion.foodFusionPlatform.rdbm.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Nick Dilallo
 * This class defines the repository for the Saved table.
 * 
 */
public interface SavedRepository extends CrudRepository<Saved, Long> {
    
}