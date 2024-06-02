package com.foodFusion.foodFusionPlatform.rdbm.users;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Nick Dilallo
 * This class defines the repository for the User table.
 * 
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
}