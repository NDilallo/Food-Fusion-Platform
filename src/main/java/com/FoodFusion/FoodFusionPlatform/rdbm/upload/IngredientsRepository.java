package com.foodFusion.foodFusionPlatform.rdbm.upload;

 import org.springframework.data.repository.CrudRepository;

 /**
 * 
 * @author Dhruvi
 * This class defines the repository for the Ingredients table.
 * 
 */
    public interface IngredientsRepository extends CrudRepository<Ingredients, Long> {
        /**
         * Finds Ingredient based on the given protein
         * @param protein
         * @return an Ingredient instance
         */
        Ingredients findByProtein(String protein);

        /**
         * Finds Ingredient based on given vegeatble
         * @param veggies
         * @return an Ingredient instance
         */
        Ingredients findByVeggies(String veggies);

        /**
         * Finds Ingredient based on the given dietary restriction
         * @param dietaryRestrictions
         * @return an Ingredient instance
         */
        Ingredients findByDietaryRestrictions(String dietaryRestrictions);
    }
    

