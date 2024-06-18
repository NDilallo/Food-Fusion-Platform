package com.foodFusion.foodFusionPlatform.rdbm.homePage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Dhruvi
 * This class defines the contents of the Chefs table.
 * 
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Chefs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chefId;

    private String name;
    private String specialty;
}
