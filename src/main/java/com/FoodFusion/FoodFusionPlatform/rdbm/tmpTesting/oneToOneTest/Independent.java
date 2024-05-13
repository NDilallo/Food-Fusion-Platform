package com.FoodFusion.FoodFusionPlatform.rdbm.tmpTesting.oneToOneTest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/* 
This file is part of an example linking two tables together one-to-one. This file is "Independent" 
because it has a primary key that is being referenced by another table called Dependent".
*/

@Data
@Entity
public class Independent {
    @Id
    @GeneratedValue
    private long id;

    private String independentKey;
}
