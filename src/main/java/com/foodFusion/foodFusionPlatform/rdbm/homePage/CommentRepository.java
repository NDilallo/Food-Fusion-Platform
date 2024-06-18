package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Matt Nice
 * This class defines the repository for the PostComments table.
 * 
 */
public interface CommentRepository extends CrudRepository<PostComments, Long> {
}
