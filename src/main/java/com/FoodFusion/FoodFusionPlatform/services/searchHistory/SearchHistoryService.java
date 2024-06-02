package com.foodFusion.foodFusionPlatform.services.searchHistory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.searchHistory.SearchHistory;
import com.foodFusion.foodFusionPlatform.rdbm.searchHistory.SearchHistoryRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Defines the services for theh SearchHistory table
 * @author Nick Dilallo
 */
@Service
@Log4j2
public class SearchHistoryService {
    @Autowired
    private SearchHistoryRepository repo;

    /**
     * list all the SearchHistory instances
     * @return a list of SearchHistory instances
     */
    public List<SearchHistory> list() {
        log.traceEntry("Enter list");
        List<SearchHistory> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);        
        return retval;
    }

    /**
     * save a new search history
     * @param user
     * @return the saved SearchHistory instance
     */
    public SearchHistory save(SearchHistory user) {
        log.traceEntry("enter save", user);
        repo.save(user);
        log.traceExit("exit save", user);        
        return user;
    }

    /**
     * delete a search history with the given id
     * @param id
     */
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }
}
