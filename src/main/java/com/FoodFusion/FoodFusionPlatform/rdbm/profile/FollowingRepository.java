package com.foodFusion.foodFusionPlatform.rdbm.profile;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FollowingRepository extends CrudRepository<Following, Long> {
    List<Following> findByUserID(long userID);
}
