package com.foodFusion.foodFusionPlatform.services.profile;



import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.profile.Settings;
import com.foodFusion.foodFusionPlatform.rdbm.profile.SettingsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SettingsService {
    @Autowired
    private SettingsRepository repo;

    public List<Settings> list() {
        log.traceEntry("Enter list");
        List<Settings> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);        
        return retval;
    }

    public Settings save(Settings settings) {
        log.traceEntry("enter save", settings);
        repo.save(settings);
        log.traceExit("exit save", settings);        
        return settings;
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }
}