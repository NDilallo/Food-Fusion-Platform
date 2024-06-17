package com.foodFusion.foodFusionPlatform.services.profile;

import java.util.List;
import java.util.Optional;
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

    public Settings findById(long id) {
        log.traceEntry("Enter findById", id);
        Optional<Settings> settings = repo.findById(id);
        log.traceExit("Exit findById", settings.orElse(null));
        return settings.orElse(null);
    }
public Settings getById(long id) {
    log.traceEntry("Enter getById", id);
    Optional<Settings> settings = repo.findById(id);
    log.traceExit("Exit getById", settings.orElse(null));
    return settings.orElse(null);
}

    public Settings save( Settings settings) {
        log.traceEntry("Enter save", settings);
        repo.save(settings);
        log.traceExit("Exit save", settings);        
        return settings;
    }

    public Settings save(long id, Settings settings) {
        log.traceEntry("Enter save", settings);
        repo.save(settings);
        log.traceExit("Exit save", settings);        
        return settings;
    }

    public Settings update(long id, Settings settings) {
        log.traceEntry("Enter update", id, settings);
        if (repo.existsById(id)) {
            settings.setSettingsID(id);
            Settings updatedSettings = repo.save(settings);
            log.traceExit("Exit update", updatedSettings);
            return updatedSettings;
        } else {
            log.traceExit("Exit update - not found", id);
            return null;
        }
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }
}
