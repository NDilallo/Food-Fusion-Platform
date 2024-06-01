package com.foodFusion.foodFusionPlatform.rdbm.upload;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class IngredientsRepositoryTest {

    @Autowired
    private IngredientsRepository ingredientsRepository;
    void testFindByProtein() {
        Ingredients ingredients = new Ingredients();
        ingredients.setProtein("Tofu");
        ingredientsRepository.save(ingredients);

        Ingredients found = ingredientsRepository.findByProtein("Tofu");

        assertThat(found).isNotNull();
        assertThat(found.getProtein()).isEqualTo("Tofu");
    }

    @Test
    void testFindByVeggies() {
        Ingredients ingredients = new Ingredients();
        ingredients.setVeggies("French Beans");
        ingredientsRepository.save(ingredients);

        Ingredients found = ingredientsRepository.findByVeggies("French Beans");

        assertThat(found).isNotNull();
        assertThat(found.getVeggies()).isEqualTo("French Beans");
    }

    @Test
    void testFindByDietaryRestrictions() {
        Ingredients ingredients = new Ingredients();
        ingredients.setDietaryRestrictions("Gluten-Free");
        ingredientsRepository.save(ingredients);

        Ingredients found = ingredientsRepository.findByDietaryRestrictions("Gluten-Free");

        assertThat(found).isNotNull();
        assertThat(found.getDietaryRestrictions()).isEqualTo("Gluten-Free");
    }
}
