package com.foodFusion.foodFusionPlatform.rdbm.users;

import org.springframework.data.repository.CrudRepository;

/**
 * @see JpaRepository that extends PagingAndSortingRepository that expends CrudRepository
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
}