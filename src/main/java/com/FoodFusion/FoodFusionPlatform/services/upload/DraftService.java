package com.foodFusion.foodFusionPlatform.services.upload;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.upload.Draft;
import com.foodFusion.foodFusionPlatform.rdbm.upload.DraftRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DraftService {

    @Autowired
    private DraftRepository draftRepository;

    public List<Draft> list() {
        log.traceEntry("Enter listAll");
        List<Draft> drafts = draftRepository.findAll();
        log.traceExit("Exit listAll", drafts);
        return drafts;
    }

    public Draft save(Draft draft) {
        log.traceEntry("Enter save", draft);
        draftRepository.save(draft);
        log.traceExit("Exit save", draft);
        return draft;
    }

    public Optional<Draft> getById(long id) {
        log.traceEntry("Enter getById", id);
        Optional<Draft> draft = draftRepository.findById(id);
        log.traceExit("Exit getById", draft);
        return draft;
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        draftRepository.deleteById(id);
        log.traceExit("Exit delete");
    }
}