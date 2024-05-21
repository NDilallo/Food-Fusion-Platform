package com.FoodFusion.FoodFusionPlatform.rdbm.HomePage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChefsService {

    private final ChefsRepository chefRepository;

    public ChefsService(ChefsRepository chefRepository) {
        this.chefRepository = chefRepository;
    }

    public String addChef(Chefs chef) {
        chefRepository.save(chef);
        return "All Chefs are added successfully!";
    }

    public List<Chefs> getAllChefs() {
        List<Chefs> chefs = new ArrayList<>();
        chefRepository.findAll().forEach(chefs::add);
        return chefs;
    }

       public String cook(String name, String role) {
        
        throw new UnsupportedOperationException("Unimplemented method 'cook'");
    }
}
