package com.foodFusion.foodFusionPlatform.rdbm.profile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 * @author Matt Nice
 * This class defines the contents of the Settings table.
 * 
 */
@Data
@Entity
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long settingsID;

    // Other fields

    @NotNull(message = "User ID must be set")
    private long userID; // Link to Profile Table

    
    @NotNull(message = "Enable Dark Mode must be set")
    private boolean enableDarkMode;
}
