package com.foodFusion.foodFusionPlatform.rdbm.users;

import com.foodFusion.foodFusionPlatform.rdbm.profile.Profile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 
 * @author Nick Dilallo 
 * This class defines the contents of the User table.
 * 
 */
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

    // @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id") // profile_id is the name of col in this table w/ the foregin key
    private Profile profile_link; // profile_link will link to the primary key of the Independent entity 
    
}
