package com.FoodFusion.FoodFusionPlatform.services.searchHistory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodFusion.FoodFusionPlatform.rdbm.searchHistory.tmp;
import com.FoodFusion.FoodFusionPlatform.rdbm.searchHistory.SearchHistoryRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SearchHistoryService {
    @Autowired
    private SearchHistoryRepository repo;

    public List<tmp> list() {
        log.traceEntry("Enter list");
        List<tmp> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);        
        return retval;
    }

    public tmp save(tmp user) {
        log.traceEntry("enter save", user);
        repo.save(user);
        log.traceExit("exit save", user);        
        return user;
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }
}
