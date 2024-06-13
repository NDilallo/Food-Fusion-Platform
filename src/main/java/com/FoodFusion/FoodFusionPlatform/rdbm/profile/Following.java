package com.foodFusion.foodFusionPlatform.rdbm.profile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 * @author Fritz Nastor
 * This class defines the contents of the Following table.
 * 
 */
@Data
@Entity
public class Following {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long followingID;

    // Other fields

    @NotNull(message = "User ID must be set")
    private long userID; // Link to Profile Table

    @NotNull(message = "Profile ID must be set")
    private long profileID; // Link to Profile Table
}
