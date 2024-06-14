package com.foodFusion.foodFusionPlatform.services.upload;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.upload.Ingredients;
import com.foodFusion.foodFusionPlatform.rdbm.upload.IngredientsRepository;

/**
 * Defines the services for the Ingredients table
 * @author Dhruvi
 */
@Service
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    /**
     * Constructor
     * @param ingredientsRepository
     */
    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    /**
     * add an ingredient
     * @param ingredient
     * @return the added Ingredient instance
     */
    public Ingredients addIngredient(Ingredients ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    /**
     * list all the ingredients
     * @return a list of Ingredient instances
     */
    public List<Ingredients> getAllIngredients() {
        return (List<Ingredients>) ingredientsRepository.findAll();
    }

    
}
