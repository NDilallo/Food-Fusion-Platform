package com.FoodFusion.FoodFusionPlatform.rdbm.tmpTesting.oneToOneTest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

/* 
This file is part of an example linking two tables together one-to-one. This file is "Dependent" 
because it has a column that is a foreign key to another table called "Independent".
*/

@Data
@Entity
public class Dependent {
    //Defining Table

    @Id
    @GeneratedValue
    private long id;
    
    @OneToOne
    @JoinColumn(name = "foreign_id") // foreign_id is the name of col in this table w/ the foregin key
    private Independent foreignKey; // foreignKey will link to the primary key of the Independent entity
}
