package com.FoodFusion.FoodFusionPlatform.rdbm.upload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientsController {

    private final IngredientsRepository ingredientsRepository;

    @PostMapping("/addProtein")
    public String addProtein(@RequestParam String protein, @RequestBody Ingredients ingredients) {
        ingredients.addProtein(protein);
        ingredientsRepository.save(ingredients);
        return "Protein added successfully!";
    }

    @PostMapping("/addVeggies")
    public String addVeggies(@RequestParam String veggies, @RequestBody Ingredients ingredients) {
        ingredients.addVegetables(veggies);
        ingredientsRepository.save(ingredients);
        return "All Vegetables added successfully!";
    }

    @PostMapping("/addDietaryRestrictions")
    public String addDietaryRestrictions(@RequestParam String dietaryRestrictions, @RequestBody Ingredients ingredients) {
        ingredients.addDietaryRestrictions(dietaryRestrictions);
        ingredientsRepository.save(ingredients);
        return "The Dietary restrictions added successfully!";
    }

    @GetMapping("/checkDietaryRestrictions")
    public String checkDietaryRestrictions(@RequestBody Ingredients ingredients) {
        if (ingredients.hasDietaryRestrictions()) {
            return "This dish has dietary restrictions.";
        } else {
            return "This dish has no dietary restrictions.";
        }
    }

    @GetMapping("/checkVegetarian")
    public String checkVegetarian(@RequestBody Ingredients ingredients) {
        if (ingredients.isVegetarian()) {
            return "This dish is vegetarian.";
        } else {
            return "This dish is not vegetarian.";
        }
    }

    @GetMapping("/checkGlutenFree")
    public String checkGlutenFree(@RequestBody Ingredients ingredients) {
        if (ingredients.isGlutenFree()) {
            return "This dish is gluten-free.";
        } else {
            return "This dish is not gluten-free.";
        }
    }

    @GetMapping("/checkLactoseDairyFree")
    public String checkLactoseFree(@RequestBody Ingredients ingredients) {
        if (ingredients.isLactoseDairyFree()) {
            return "This dish is lactose and Dairy-free.";
        } else {
            return "This dish is not lactose and Dairy-free.";
        }
    }
}
