package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import org.hibernate.annotations.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
}
