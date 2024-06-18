package com.foodFusion.foodFusionPlatform.controller.upload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodFusion.foodFusionPlatform.rdbm.upload.Ingredients;
import com.foodFusion.foodFusionPlatform.rdbm.upload.IngredientsRepository;

import lombok.RequiredArgsConstructor;

/**
 * Controller for managing Ingredients entities.
 * @author Dhruvi
 */
@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientsController {

    private final IngredientsRepository ingredientsRepository;

    /**
     * Add a protein to an Ingredient
     * @param protein
     * @param ingredients
     * @return String success message
     */
    @PostMapping("/addProtein")
    public String addProtein(@RequestParam String protein, @RequestBody Ingredients ingredients) {
        ingredients.addProtein(protein);
        ingredientsRepository.save(ingredients);
        return "Protein added successfully!";
    }

    /**
     * Add a veggie to an Ingredient
     * @param veggies
     * @param ingredients
     * @return String success message
     */
    @PostMapping("/addVeggies")
    public String addVeggies(@RequestParam String veggies, @RequestBody Ingredients ingredients) {
        ingredients.addVegetables(veggies);
        ingredientsRepository.save(ingredients);
        return "All Vegetables added successfully!";
    }

    /**
     * Add a dietary restriction to an Ingredient
     * @param dietaryRestrictions
     * @param ingredients
     * @return String success message
     */
    @PostMapping("/addDietaryRestrictions")
    public String addDietaryRestrictions(@RequestParam String dietaryRestrictions, @RequestBody Ingredients ingredients) {
        ingredients.addDietaryRestrictions(dietaryRestrictions);
        ingredientsRepository.save(ingredients);
        return "The Dietary restrictions added successfully!";
    }

    /**
     * Chech if a given ingredient has dietary restrictions
     * @param ingredients
     * @return String message
     */
    @GetMapping("/checkDietaryRestrictions")
    public String checkDietaryRestrictions(@RequestBody Ingredients ingredients) {
        if (ingredients.hasDietaryRestrictions()) {
            return "This dish has dietary restrictions.";
        } else {
            return "This dish has no dietary restrictions.";
        }
    }

    /**
     * Check if an ingredient is vegetarian
     * @param ingredients
     * @return String message
     */
    @GetMapping("/checkVegetarian")
    public String checkVegetarian(@RequestBody Ingredients ingredients) {
        if (ingredients.isVegetarian()) {
            return "This dish is vegetarian.";
        } else {
            return "This dish is not vegetarian.";
        }
    }

    /**
     * Check if ingredient is gluten free
     * @param ingredients
     * @return String message 
     */
    @GetMapping("/checkGlutenFree")
    public String checkGlutenFree(@RequestBody Ingredients ingredients) {
        if (ingredients.isGlutenFree()) {
            return "This dish is gluten-free.";
        } else {
            return "This dish is not gluten-free.";
        }
    }

    /**
     * Check if ingredient is lactose and dairy free
     * @param ingredients
     * @return String message
     */
    @GetMapping("/checkLactoseDairyFree")
    public String checkLactoseFree(@RequestBody Ingredients ingredients) {
        if (ingredients.isLactoseDairyFree()) {
            return "This dish is lactose and Dairy-free.";
        } else {
            return "This dish is not lactose and Dairy-free.";
        }
    }
}
