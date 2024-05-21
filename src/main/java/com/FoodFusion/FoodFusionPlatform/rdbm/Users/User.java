package com.FoodFusion.FoodFusionPlatform.rdbm.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@Table(name = "app_user") // Escape the table name "user" to avoid conflicts with reserved keywords
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min= 3,max = 15, message = "username must be between 3 and 15 characters")
    private String username;

    @NotBlank
    @Size(min= 3,max = 15, message = "password must be between 3 and 15 characters")
    private String pass;

    /*
    @OneToOne
    @JoinColumn(name = "profile_id") // profile_id is the name of col in this table w/ the foregin key
    private Profile profile_link; // profile_link will link to the primary key of the Independent entity 
    */
}
