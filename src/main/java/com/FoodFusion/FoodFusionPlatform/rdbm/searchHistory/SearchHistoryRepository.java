package com.foodFusion.foodFusionPlatform.rdbm.searchHistory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @see JpaRepository that extends PagingAndSortingRepository that expends CrudRepository
 */
public interface SearchHistoryRepository extends CrudRepository<SearchHistory, Long> {
    
}