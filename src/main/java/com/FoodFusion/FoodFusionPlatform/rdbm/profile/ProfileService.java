package com.FoodFusion.FoodFusionPlatform.rdbm.Profile;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping("/api/profiles")
@Log4j2
public class ProfileService {
    @Autowired
    private ProfileRepository repo;

    public List<Profile> list(){
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return repo.findAll();   
    }


}
