package com.foodFusion.foodFusionPlatform.services.upload;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.upload.Draft;
import com.foodFusion.foodFusionPlatform.rdbm.upload.DraftRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Defines the services for the Drafts table
 * @author Matt Nice
 */
@Service
@Log4j2
public class DraftService {

    @Autowired
    private DraftRepository draftRepository;

    /**
     * list all the drafts
     * @return a list of Draft instances
     */
    public List<Draft> listAll() {
        log.traceEntry("Enter listAll");
        List<Draft> drafts = draftRepository.findAll();
        log.traceExit("Exit listAll", drafts);
        return drafts;
    }

    /**
     * Save a new draft
     * @param draft
     * @return the saved Draft instance
     */
    public Draft save(Draft draft) {
        log.traceEntry("Enter save", draft);
        Draft savedDraft = draftRepository.save(draft);
        log.traceExit("Exit save", savedDraft);
        return savedDraft;
    }

    /**
     * Retrieve a draft with the given id
     * @param id
     * @return the retrieved Draft if it exists
     */
    public Optional<Draft> getById(long id) {
        log.traceEntry("Enter getById", id);
        Optional<Draft> draft = draftRepository.findById(id);
        log.traceExit("Exit getById", draft);
        return draft;
    }

    /**
     * delete a draft with the given id
     * @param id
     */
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        draftRepository.deleteById(id);
        log.traceExit("Exit delete");
    }
}