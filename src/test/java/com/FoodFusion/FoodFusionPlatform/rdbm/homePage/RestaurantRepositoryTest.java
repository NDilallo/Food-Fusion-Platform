package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Restaurant;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.RestaurantRepository;

@SpringBootTest
public class RestaurantRepositoryTest {
    @Autowired
    private RestaurantRepository repo;

    @BeforeEach
    public void addRestaurants() {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setAddress("123 W Dr");
        restaurant1.setCuisine("American");
        restaurant1.setName("Burgers!");
        repo.save(restaurant1);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setAddress("782 S Dr");
        restaurant2.setCuisine("Asian");
        restaurant2.setName("Noodles!");
        repo.save(restaurant2);

        Restaurant restaurant3 = new Restaurant();
        restaurant3.setAddress("855 S Dr");
        restaurant3.setCuisine("American");
        restaurant3.setName("Burgers!");
        repo.save(restaurant3);
    }

     /**
     * Test create, read, update, and delete
     */
    @Test
    public void testCrud() {
        Restaurant rtest = new Restaurant();
        rtest.setAddress("311 W Dr");
        rtest.setCuisine("American");
        rtest.setName("Burgers");
        long b4count = repo.count();
        long b4id = rtest.getRestaurantId();
        repo.save(rtest);
        long aftercount = repo.count();
        long afterid = rtest.getRestaurantId();

        // there should be 1 more in the database after the save
        assertEquals(b4count + 1, aftercount);

        // original id was 0 but afterwards the id was generated and so should not be equal
        assertNotEquals(b4id, afterid);

        // Scenario of updating cross listing
        // Be sure to find the reference from the database before the update
        Restaurant updated = repo.findByRestaurantId(afterid);
        updated.setAddress("312 E Dr");
        repo.save(updated);

        Restaurant updatedCheck = repo.findByRestaurantId(afterid);
        assertNotEquals(updatedCheck, rtest);
        assertEquals("312 E Dr", updatedCheck.getAddress());

        b4count = repo.count();
        repo.delete(updatedCheck);
        aftercount = repo.count();
        assertEquals(b4count - 1, aftercount);
    }   

    @Test
    public void findByAddress() {
        long count = repo.count();
        Restaurant addr = repo.findByAddress("123 W Dr");

        assertNotEquals(0, count);
        assertEquals("123 W Dr", addr.getAddress());
        assertEquals("Burgers!", addr.getName());
    }

    @Test
    public void findByCuisine() {
        long count = repo.count();
        List<Restaurant> american = repo.findByCuisine("American");
        List<Restaurant> asian = repo.findByCuisine("Asian");
        List<Restaurant> ethiopian = repo.findByCuisine("Ethiopian");

        assertNotEquals(0, count);
        assertEquals(2, american.size());
        assertEquals(1, asian.size());
        assertEquals(0, ethiopian.size());
    }

    @Test
    public void findByName() {
        long count = repo.count();
        List<Restaurant> burger = repo.findByName("Burgers!");
        List<Restaurant> noodles = repo.findByName("Noodles!");
        List<Restaurant> eggs = repo.findByCuisine("Eggs!");

        assertNotEquals(0, count);
        assertEquals(2, burger.size());
        assertEquals(1, noodles.size());
        assertEquals(0, eggs.size());
    }
}
