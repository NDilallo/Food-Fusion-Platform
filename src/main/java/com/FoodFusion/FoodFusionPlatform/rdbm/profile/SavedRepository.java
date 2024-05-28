package com.foodFusion.FoodFusionPlatform.rdbm.profile;

import org.springframework.data.repository.CrudRepository;

/**
 * @see JpaRepository that extends PagingAndSortingRepository that expends CrudRepository
 */
public interface SavedRepository extends CrudRepository<Saved, Long> {
    
}