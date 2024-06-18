package com.foodFusion.foodFusionPlatform.rdbm.searchHistory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Nick Dilallo
 * This class defines the repository for the SearchHistory table.
 * 
 */
public interface SearchHistoryRepository extends CrudRepository<SearchHistory, Long> {
    
}