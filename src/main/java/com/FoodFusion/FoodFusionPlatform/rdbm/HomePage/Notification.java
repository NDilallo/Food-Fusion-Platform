package com.FoodFusion.FoodFusionPlatform.rdbm.HomePage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "User link must be set")
    private long userId; // Link to the user that followed you

    @NotBlank(message = "Notification type must be set")
    private String notificationType; // Type of notification (liked, reposted, etc)
}
