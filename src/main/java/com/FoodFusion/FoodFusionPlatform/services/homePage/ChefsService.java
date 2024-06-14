package com.foodFusion.foodFusionPlatform.services.homePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Chefs;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.ChefsRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Define services for the Chefs table
 * @author Dhruvi
 */
@Service
@Log4j2
public class ChefsService {

    private final ChefsRepository chefRepository;

    /**
     * List all chefs in the table
     * @return a list of Chefs
     */
    public List<Chefs> listAll() {
        log.traceEntry("Enter listAll");
        List<Chefs> comments = StreamSupport.stream(chefRepository.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit listAll", comments);
        return comments;
    }

    /**
     * Save a chef instance 
     * @param chef
     * @return the saved chef
     */
    public Chefs save(Chefs chef) {
        log.traceEntry("Enter save", chef);
        Chefs savedChef = chefRepository.save(chef);
        log.traceExit("Exit save", savedChef);
        return savedChef;
    }

    /**
     * Retrieve the chef with the given id
     * @param id
     * @return the retrieved chef
     */
    public Optional<Chefs> getById(long id) {
        log.traceEntry("Enter getById", id);
        Optional<Chefs> chefs = chefRepository.findById(id);
        log.traceExit("Exit getById", chefs);
        return chefs;
    }

    /**
     * delete the chef with the given id
     * @param id
     */
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        chefRepository.deleteById(id);
        log.traceExit("Exit delete");
    }

    /**
     * Constructor
     */
    public ChefsService(ChefsRepository chefRepository) {
        this.chefRepository = chefRepository;
    }

    /**
     * Add a chef
     * @param chef
     * @return string success message
     */
    public String addChef(Chefs chef) {
        chefRepository.save(chef);
        return "All Chefs are added successfully!";
    }

    /**
     * List all chefs
     * @return a list of the Chefs
     */
    public List<Chefs> getAllChefs() {
        List<Chefs> chefs = new ArrayList<>();
        chefRepository.findAll().forEach(chefs::add);
        return chefs;
    }

       public String cook(String name, String role) {
        
        throw new UnsupportedOperationException("Unimplemented method 'cook'");
    }
}
