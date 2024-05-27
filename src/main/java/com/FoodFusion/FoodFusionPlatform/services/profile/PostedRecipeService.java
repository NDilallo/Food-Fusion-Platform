package com.foodFusion.FoodFusionPlatform.services.profile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.profile.PostedRecipe;
import com.foodFusion.foodFusionPlatform.rdbm.profile.PostedRecipeRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PostedRecipeService {
    @Autowired
    private PostedRecipeRepository repo;

    public List<PostedRecipe> list() {
        log.traceEntry("Enter List");
        List<PostedRecipe> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);
        return retval;
    }

    public PostedRecipe save(PostedRecipe postedRecipe) {
        log.traceEntry("enter save", postedRecipe);
        repo.save(postedRecipe);
        log.traceExit("Exit list", postedRecipe);
        return postedRecipe;                
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }
}
