package com.foodFusion.foodFusionPlatform.services.profile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Rating;
import com.foodFusion.foodFusionPlatform.rdbm.profile.PostedRecipe;
import com.foodFusion.foodFusionPlatform.rdbm.profile.PostedRecipeRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class PostedRecipeService {

    @Autowired
    private PostedRecipeRepository repo;

    public List<PostedRecipe> list() {
        log.traceEntry("Enter list");
        List<PostedRecipe> retval = StreamSupport.stream(repo.findAll().spliterator(), false)
                                                .collect(Collectors.toList());
        log.traceExit("Exit list", retval);
        return retval;
    }

    public PostedRecipe save(PostedRecipe postedRecipe) {
        log.traceEntry("Enter save", postedRecipe);
        repo.save(postedRecipe);
        log.traceExit("Exit save", postedRecipe);
        return postedRecipe;
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

    public Optional<PostedRecipe> findById(long id) {
        log.traceEntry("Enter findById", id);
        Optional<PostedRecipe> recipe = repo.findById(id);
        log.traceExit("Exit findById", recipe);
        return recipe;
    }

    public PostedRecipe addRating(long recipeId, double ratingValue) {
        log.traceEntry("Enter addRating", recipeId, ratingValue);
        Optional<PostedRecipe> optionalRecipe = repo.findById(recipeId);
        if (optionalRecipe.isPresent()) {
            PostedRecipe recipe = optionalRecipe.get();
            List<Rating> ratings = recipe.getRatings();
            if (ratings == null) {
                ratings = List.of(); // Initialize ratings if null
            }
            Rating newRating = new Rating();
            newRating.setRating(ratingValue);
            ratings.add(newRating);
            recipe.setRatings(ratings);

            double avgRating = recipe.getRatings().stream()
                                        .mapToDouble(Rating::getRating)
                                        .average()
                                        .orElse(0.0);
            recipe.setAvgRating(avgRating);

            repo.save(recipe); // Save the updated recipe
            log.traceExit("Exit addRating", recipe);
            return recipe;
        } else {
            log.warn("Recipe with ID {} not found", recipeId);
            return null;
        }
    }
}
