package com.FoodFusion.FoodFusionPlatform.rdbm.TmpTesting;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * Test code for database
 */
@Data
@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Test dept must be set")
    @Size(min= 2,max = 5, message = "dept must be between 2 and 5 characters")
    private String dept;

    @NotBlank
    @Size(min= 2,max = 7, message = "dept num must be between 2 and 6 characters")
    private String num;

}