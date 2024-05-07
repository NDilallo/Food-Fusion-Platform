package com.FoodFusion.FoodFusionPlatform.rdbm.home;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="Restaurants")
public class Restaurant {
    /* Unsure if this is necessary
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "restaurant_recs",
        joinColumns = @JoinColumn(name = ))
    */
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
