package com.FoodFusion.FoodFusionPlatform.rdbm.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.FoodFusion.FoodFusionPlatform.rdbm.users.User;
import com.FoodFusion.FoodFusionPlatform.rdbm.users.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

    String originalName = "Nick Original";
    String originalPass = "abc123";

    String updatedPass = "def456";
    String updatedName = "Nick Updated";

    @Autowired
    private UserRepository repo;

     /**
     * Test create, read, update, and delete
     */
    @Test
    public void testCrud(){
        User utest = new User();
        utest.setUsername(originalName);
        utest.setPass(originalPass);
        long b4count = repo.count();
        long b4id = utest.getId();
        repo.save(utest);
        long aftercount = repo.count();
        long afterid = utest.getId();

        // there should be 1 more in the database after the save
        assertEquals(b4count + 1, aftercount);

        // original id was 0 but afterwards the id was generated and so should not be equal
        assertNotEquals(b4id, afterid);

        // Scenario of updating username
        // Be sure to find the reference from the database before the update
        User updatedUsername = repo.findById(afterid).get();
        updatedUsername.setUsername(updatedName);
        repo.save(updatedUsername);

        User updatedCheck = repo.findById(afterid).get();
        assertNotEquals(updatedCheck, utest);
        assertEquals(updatedName, updatedCheck.getUsername());

        // Scenario of updating password
        User updatePass = repo.findById(afterid).get();
        updatePass.setPass(updatedPass);
        repo.save(updatePass);

        updatedCheck = repo.findById(afterid).get();
        assertNotEquals(updatedCheck, utest);
        assertEquals(updatedPass, updatedCheck.getPass());

        //Test deletion of a user
        b4count = repo.count();
        repo.delete(updatedCheck);
        aftercount = repo.count();
        assertEquals(b4count - 1, aftercount);

    }    
}
