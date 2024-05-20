package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="Restaurants")
public class Restaurant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long restaurantId;

    @NotBlank(message = "Cuisine must be set.")
    @Size(min= 2, message = "Cuisine must be 2 or more characters")
    private String cuisine;

    @NotBlank(message = "Name must be set")
    @Size(min= 2, message = "Name must be 2 or more characters")
    private String name;

    @NotBlank(message = "Address must be set")
    @Size(min= 2, message = "Address must be 2 or more characters")
    private String address;
}
