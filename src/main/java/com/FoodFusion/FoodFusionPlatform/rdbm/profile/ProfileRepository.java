package com.foodFusion.foodFusionPlatform.rdbm.profile;

import org.springframework.data.repository.CrudRepository;

/**
 * @see JpaRepository that extends PagingAndSortingRepository that expends CrudRepository
 */
public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
