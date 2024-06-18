package com.foodFusion.foodFusionPlatform.rdbm.searchHistory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 
 * @author Nick Dilallo
 * This class defines the contents of the SearchHistory table.
 * 
 */
@Data
@Entity
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String recent_searches;
}
