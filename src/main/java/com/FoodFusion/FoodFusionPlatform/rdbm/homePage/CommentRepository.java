package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<PostComments, Long> {
}
