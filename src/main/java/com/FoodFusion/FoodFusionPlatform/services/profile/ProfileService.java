package com.foodFusion.foodFusionPlatform.services.profile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.profile.Profile;
import com.foodFusion.foodFusionPlatform.rdbm.profile.ProfileRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProfileService {
    @Autowired
    private ProfileRepository repo;

    public List<Profile> list() {
        log.traceEntry("Enter list");
        List<Profile> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);        
        return retval;
    }

    public Profile save(Profile profile) {
        log.traceEntry("enter save", profile);
        Profile savedProfile = repo.save(profile);
        log.traceExit("exit save", profile);        
        return savedProfile;
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

    public Profile findById(long id) {
        log.traceEntry("Enter findById", id);
        Profile profile = repo.findById(id).orElse(null);
        log.traceExit("Exit findById", profile);
        return profile;
    }
}
