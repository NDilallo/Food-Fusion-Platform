package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ChefsRepositoryTest {

    @Autowired
    private ChefsRepository chefRepository;

    @Test
    void testFindByName() {
        Chefs chef = new Chefs();
        chef.setName("Gordon Ramsay");
        chef.setSpecialty("International Cuisine");
        chefRepository.save(chef);

        Chefs found = chefRepository.findByName("Gordon Ramsay");
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Gordon Ramsay");
    }

    @Test
    void testfindSpecialtyChefs() {
        Chefs chef = new Chefs();
        chef.setName("Jamie Oliver");
        chef.setSpecialty("Italian Cuisine");
        chefRepository.save(chef);

        Chefs found = chefRepository.findSpecialtyChef("Italian Cuisine");
        assertThat(found).isNotNull();
        assertThat(found.getSpecialty()).isEqualTo("Italian Cuisine");
    }
}
