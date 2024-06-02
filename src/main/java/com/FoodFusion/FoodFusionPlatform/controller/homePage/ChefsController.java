package com.foodFusion.foodFusionPlatform.controller.homePage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Chefs;
import com.foodFusion.foodFusionPlatform.services.homePage.ChefsService;

/**
 * 
 * @author Dhruvi
 * This class defines the controller for the Chefs table.
 * 
 */
@SpringBootApplication
public class ChefsController {

    public static void main(String[] args) {
        SpringApplication.run(ChefsController.class, args);
    }
}

@RestController
@RequestMapping("/chef")
class ChefController {

    @Autowired
    private ChefsService chefService;

    /**
     * Add a chef to the Chef table
     * @param chef
     * @return string
     */
    @PostMapping("/add")
    public String addChef(@RequestBody Chefs chef) {
        return chefService.addChef(chef);
    }

    /**
     * Get all the chefs from the Chef table
     * @return a list of Chef instances
     */
    @GetMapping("/all")
    public List<Chefs> getAllChefs() {
        return chefService.getAllChefs();
    }
}
