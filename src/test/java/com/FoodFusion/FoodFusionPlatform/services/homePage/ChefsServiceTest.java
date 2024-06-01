package com.foodFusion.foodFusionPlatform.services.homePage;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Chefs;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.ChefsRepository;

@ExtendWith(MockitoExtension.class)
public class ChefsServiceTest {

    @Mock
    private ChefsRepository chefRepository;

    @InjectMocks
    private ChefsService chefService;

    private Chefs chef;

    @BeforeEach
    void setUp() {
        chef = new Chefs(1L, "Gordon Ramsay", "International Cuisine");
    }

    @Test
    void testAddChef() {
        given(chefRepository.save(chef)).willReturn(chef);

        Chefs savedChef = chefService.addChef(chef);

        assertThat(savedChef).isNotNull();
        verify(chefRepository).save(chef);
    }

    @Test
    void testGetAllChefs() {
        given(chefRepository.findAll()).willReturn(Collections.singletonList(chef));

        List<Chefs> chefs = chefService.getAllChefs();

        assertThat(chefs).hasSize(1);
        verify(chefRepository).findAll();
    }

    @Test
    void testGetChefByName() {
        given(chefRepository.findByName("Gordon Ramsay")).willReturn(chef);

        Chefs foundChef = chefService.getChefByName("Gordon Ramsay");

        assertThat(foundChef).isNotNull();
        assertThat(foundChef.getName()).isEqualTo("Gordon Ramsay");
        verify(chefRepository).findByName("Gordon Ramsay");
    }

    @Test
    void testFindSpecialtyChef() {
        given(chefRepository.findSpecialtyChef("International Cuisine")).willReturn(chef);

        Chefs foundChef = chefService.getChefBySpecialtyChefs("International Cuisine");

        assertThat(foundChef).isNotNull();
        assertThat(foundChef.getSpecialty()).isEqualTo("International Cuisine");
        verify(chefRepository).findSpecialtyChef("International Cuisine");
    }
}
