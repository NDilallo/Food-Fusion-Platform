package com.FoodFusion.FoodFusionPlatform.rdbm.upload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DraftRepositoryTest {

    @Autowired
    private DraftRepository repo;

    /**
     * Test create, read, update, and delete
     */
    @Test
    public void testCrud() {
        Draft draft = new Draft();
        draft.setRecipeName("Test Recipe");
        draft.setDescription("This is a test description.");
        draft.setIngredients("Test ingredients");
        draft.setDraftNotes("These are some draft notes.");
        draft.setCuisine("Test Cuisine");

        long b4count = repo.count();
        repo.save(draft);
        long aftercount = repo.count();
        long afterid = draft.getRecipeId();

        // there should be 1 more in the database after the save
        assertEquals(b4count + 1, aftercount);

        // original id was 0 but afterwards the id was generated and so should not be equal
        assertNotEquals(0, afterid);

        // Scenario of updating the draft
        // Be sure to find the reference from the database before the update
        Draft updated = repo.findById(afterid).orElseThrow();
        updated.setRecipeName("Updated Recipe Name");
        repo.save(updated);

        Draft updatedCheck = repo.findById(afterid).orElseThrow();
        assertNotEquals(updatedCheck.getRecipeName(), draft.getRecipeName());
        assertEquals("Updated Recipe Name", updatedCheck.getRecipeName());

        b4count = repo.count();
        repo.delete(updatedCheck);
        aftercount = repo.count();
        assertEquals(b4count - 1, aftercount);
    }    
}
