package com.foodFusion.foodFusionPlatform.services.profile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.profile.Following;
import com.foodFusion.foodFusionPlatform.rdbm.profile.FollowingRepository;

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

    public List<Following> listByUserId(long userId) {
        log.traceEntry("Enter listByUserId");
        List<Following> retval = repo.findByUserID(userId);
        log.traceExit("Exit listByUserId", retval);
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
