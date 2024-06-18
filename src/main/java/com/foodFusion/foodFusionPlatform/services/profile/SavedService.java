package com.foodFusion.foodFusionPlatform.services.profile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.profile.Saved;
import com.foodFusion.foodFusionPlatform.rdbm.profile.SavedRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Defines the services for the Saved table
 * @author Nick Dilallo
 */
@Service
@Log4j2
public class SavedService {
    @Autowired
    private SavedRepository repo;

    /**
     * list all the Saved posts
     * @return a list of Saved instances
     */
    public List<Saved> list() {
        log.traceEntry("Enter list");
        List<Saved> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);        
        return retval;
    }

    /**
     * save a saved post
     * @param user
     * @return the saved instance
     */
    public Saved save(Saved user) {
        log.traceEntry("enter save", user);
        repo.save(user);
        log.traceExit("exit save", user);        
        return user;
    }

    /**
     * delete a saved post with the given id
     * @param id
     */
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }
}
