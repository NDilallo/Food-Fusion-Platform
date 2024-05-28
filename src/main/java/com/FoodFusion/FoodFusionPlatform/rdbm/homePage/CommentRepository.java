package com.foodFusion.FoodFusionPlatform.rdbm.homePage;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<PostComments, Long> {
}
