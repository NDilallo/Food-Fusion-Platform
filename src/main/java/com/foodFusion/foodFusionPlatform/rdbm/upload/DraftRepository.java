package com.foodFusion.foodFusionPlatform.rdbm.upload;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Matt Nice
 * This class defines the repository for the Draft table.
 * 
 */
@Repository
public interface DraftRepository extends JpaRepository<Draft, Long> {
}