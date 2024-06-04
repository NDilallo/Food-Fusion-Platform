package com.foodFusion.foodFusionPlatform.services.upload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foodFusion.foodFusionPlatform.rdbm.upload.Draft;
import com.foodFusion.foodFusionPlatform.rdbm.upload.DraftRepository;

public class DraftServiceTest {

    @Mock
    private DraftRepository draftRepository;

    @InjectMocks
    private DraftService draftService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAll() {
        // given
        Draft draft1 = new Draft();
        draft1.setRecipeId(1L);
        draft1.setRecipeName("Recipe 1");
        draft1.setDescription("Description 1");
        draft1.setIngredients("Ingredients 1");
        draft1.setCuisine("Cuisine 1");

        Draft draft2 = new Draft();
        draft2.setRecipeId(2L);
        draft2.setRecipeName("Recipe 2");
        draft2.setDescription("Description 2");
        draft2.setIngredients("Ingredients 2");
        draft2.setCuisine("Cuisine 2");

        when(draftRepository.findAll()).thenReturn(Arrays.asList(draft1, draft2));

        // when
        List<Draft> drafts = draftService.listAll();

        // then
        assertEquals(2, drafts.size());
        verify(draftRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        // given
        Draft draft = new Draft();
        draft.setRecipeName("Test Recipe");
        draft.setDescription("Test Description");
        draft.setIngredients("Test Ingredients");
        draft.setCuisine("Test Cuisine");

        when(draftRepository.save(any(Draft.class))).thenReturn(draft);

        // when
        Draft savedDraft = draftService.save(draft);

        // then
        assertEquals("Test Recipe", savedDraft.getRecipeName());
        verify(draftRepository, times(1)).save(draft);
    }

    @Test
    public void testGetById() {
        // given
        Draft draft = new Draft();
        draft.setRecipeId(1L);
        draft.setRecipeName("Test Recipe");
        draft.setDescription("Test Description");
        draft.setIngredients("Test Ingredients");
        draft.setCuisine("Test Cuisine");

        when(draftRepository.findById(1L)).thenReturn(Optional.of(draft));

        // when
        Optional<Draft> foundDraft = draftService.getById(1L);

        // then
        assertTrue(foundDraft.isPresent());
        assertEquals("Test Recipe", foundDraft.get().getRecipeName());
        verify(draftRepository, times(1)).findById(1L);
    }

    @Test
    public void testDelete() {
        // given
        long id = 1L;

        doNothing().when(draftRepository).deleteById(id);

        // when
        draftService.delete(id);

        // then
        verify(draftRepository, times(1)).deleteById(id);
    }
}
