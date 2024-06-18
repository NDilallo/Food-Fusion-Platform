package com.foodFusion.foodFusionPlatform.services.homePage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Chefs;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.ChefsRepository;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Service
@Log4j2
    public class ChefsService {

    
    @Autowired
    //private static ChefsRepository chefRepository = new ChefsRepository() {
    private final ChefsRepository chefRepository;
    public ChefsService(ChefsRepository chefRepository) {
            this.chefRepository = chefRepository;
    };

    public Chefs addChef(Chefs chef) {
        return chefRepository.save(chef);
    }

    public List<Chefs> getAllChefs() {
        return (List<Chefs>) chefRepository.findAll();
    }

    public Chefs getChefByName(String name) {
        return chefRepository.findByName(name);
    }
    // public Chefs getChefBySpecialtyChefs(String name) {
    //     return chefRepository.findSpecialtyChef(name);
    // }
}

    // private final ChefsRepository chefRepository;

    // public List<Chefs> listAll() {
    //     log.traceEntry("Enter listAll");
    //     List<Chefs> comments = StreamSupport.stream(chefRepository.findAll().spliterator(), false).collect(Collectors.toList());
    //     log.traceExit("Exit listAll", comments);
    //     return comments;
    // }

    // public Chefs save(Chefs chef) {
    //     log.traceEntry("Enter save", chef);
    //     Chefs savedChef = chefRepository.save(chef);
    //     log.traceExit("Exit save", savedChef);
    //     return savedChef;
    // }

    // public Optional<Chefs> getById(long id) {
    //     log.traceEntry("Enter getById", id);
    //     Optional<Chefs> chefs = chefRepository.findById(id);
    //     log.traceExit("Exit getById", chefs);
    //     return chefs;
    // }

    // public void delete(long id) {
    //     log.traceEntry("Enter delete", id);
    //     chefRepository.deleteById(id);
    //     log.traceExit("Exit delete");
    // }

    // public ChefsService(ChefsRepository chefRepository) {
    //     this.chefRepository = chefRepository;
    // }

    // public String addChef(Chefs chef) {
    //     chefRepository.save(chef);
    //     return "All Chefs are added successfully!";
    // }

    // public List<Chefs> getAllChefs() {
    //     List<Chefs> chefs = new ArrayList<>();
    //     chefRepository.findAll().forEach(chefs::add);
    //     return chefs;
    // }

    //    public String cook(String name, String role) {
        
    //     throw new UnsupportedOperationException("Unimplemented method 'cook'");
    // }

    // public Chefs getChefsByName(Chefs name) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getChefsByName'");
    // }

