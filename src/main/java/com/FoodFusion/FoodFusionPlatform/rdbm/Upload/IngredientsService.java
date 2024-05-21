package com.FoodFusion.FoodFusionPlatform.rdbm.Upload;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    
    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public Ingredients addIngredient(Ingredients ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    public List<Ingredients> getAllIngredients() {
        return (List<Ingredients>) ingredientsRepository.findAll();
    }

    
}
