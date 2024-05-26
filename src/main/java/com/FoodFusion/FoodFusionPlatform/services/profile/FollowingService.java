package com.FoodFusion.FoodFusionPlatform.services.profile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodFusion.FoodFusionPlatform.rdbm.profile.Following;
import com.FoodFusion.FoodFusionPlatform.rdbm.profile.FollowingRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FollowingService {
    @Autowired
    private FollowingRepository repo;

    public List<Following> list() {
        log.traceEntry("Enter list");
        List<Following> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);
        return retval;
    }

    public Following save(Following following) {
        log.traceEntry("enter save", following);
        repo.save(following);
        log.traceExit("exit save", following);
        return following;
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }
}
