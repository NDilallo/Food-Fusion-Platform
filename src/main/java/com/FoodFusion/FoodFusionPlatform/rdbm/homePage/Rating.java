package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Marisa Ban
 * This class defines the contents of the Rating table.
 * 
 */
@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
@Document("ratings")
public class Rating {
    @Id
    private String id = UUID.randomUUID().toString();

    @Setter
    private long recipeId;

    @Setter
    private double rating;
}
