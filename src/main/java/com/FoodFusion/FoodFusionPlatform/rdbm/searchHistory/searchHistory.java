package com.FoodFusion.FoodFusionPlatform.rdbm.searchHistory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "search history") // Escape the table name "user" to avoid conflicts with reserved keywords
public class searchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String recent_searches;
    private String recent_recipes;
}
