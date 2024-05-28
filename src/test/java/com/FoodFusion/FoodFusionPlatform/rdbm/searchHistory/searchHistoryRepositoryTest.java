package com.FoodFusion.FoodFusionPlatform.rdbm.searchHistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class searchHistoryRepositoryTest {
    String testSearch = "How to cook a perfect steak";
    String testRecipe = "Chicken Parmesean";
    String updatedSearch = "Best ways to grill salmon";
    String updatedRecipe = "Filet Mignon";

    @Autowired
    private SearchHistoryRepository repo;

    private SearchHistory createAndSaveSearchHistory(String search, String recipe) {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setRecent_searches(search);
        searchHistory.setRecent_recipes(recipe);
        repo.save(searchHistory);
        return searchHistory;
    }

    @Test
    public void testCreate(){
        long b4count = repo.count();
        SearchHistory stest = createAndSaveSearchHistory(testSearch, testRecipe);
        long aftercount = repo.count();

        // there should be 1 more in the database after the save
        assertEquals(b4count + 1, aftercount);
        // id should be generated and thus not be 0
        assertNotEquals(0, stest.getId());
    }

    @Test
    public void testUpdateSearch(){
        // First, create and save a new SearchHistory entry
        SearchHistory stest = createAndSaveSearchHistory(testSearch, testRecipe);
        long id = stest.getId();

        // Update the search terms
        stest.setRecent_searches(updatedSearch);
        stest.setRecent_recipes(updatedRecipe);
        repo.save(stest);

        // Retrieve the updated entry
        SearchHistory updatedStest = repo.findById(id).get();

        // Verify the search term has been updated
        assertEquals(updatedSearch, updatedStest.getRecent_searches());
        // Verify the recipe search has changed
        assertEquals(updatedRecipe, updatedStest.getRecent_recipes());
    }
}
