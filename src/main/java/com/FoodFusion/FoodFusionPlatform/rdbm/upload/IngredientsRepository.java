package com.FoodFusion.FoodFusionPlatform.rdbm.Upload;

 import org.springframework.data.repository.CrudRepository;

    public interface IngredientsRepository extends CrudRepository<Ingredients, Long> {
        Ingredients findByProtein(String protein);
        Ingredients findByVeggies(String veggies);
        Ingredients findByDietaryRestrictions(String dietaryRestrictions);
    }
    

