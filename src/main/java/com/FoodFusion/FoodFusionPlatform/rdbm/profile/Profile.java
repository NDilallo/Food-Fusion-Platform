package com.FoodFusion.FoodFusionPlatform.rdbm.profile;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;

    // Other fields

    // Foreign keys
    @NotNull(message = "Posted recipes table ID must be set")
    private long postedRecipesTableID; // Link to Posted Recipes Table
    
    @NotNull(message = "Settings table ID must be set")
    private long settingsTableID; // Link to Settings Table
    
    @NotNull(message = "Following table ID must be set")
    private long followingTableID; // Link to Following Table
    
    @NotNull(message = "Drafts table ID must be set")
    private long draftsTableID; // Link to Drafts Table

    // Foreign keys referencing Comment and Notification tables
    @NotNull(message = "Comment ID must be set")
    private long commentID; // Link to Comment Table
    
    @NotNull(message = "Notification ID must be set")
    private long notificationID; // Link to Notification Table
}
