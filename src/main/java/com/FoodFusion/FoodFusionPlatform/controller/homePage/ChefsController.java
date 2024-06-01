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

@SpringBootApplication
public class ChefsController {

    public static void main(String[] args) {
        SpringApplication.run(ChefsController.class, args);
    }
}

@RestController
@RequestMapping("/api/chef")
class ChefController {

    @Autowired
    private ChefsService chefService;

    @PostMapping
    public Chefs addChef(@RequestBody Chefs chef) {
        return chefService.addChef(chef);
    }

    @GetMapping
    public List<Chefs> getAllChefs() {
        return chefService.getAllChefs();
    }

    @GetMapping("/find")
    public Chefs getChefByspecialtyChefs(@RequestBody String name) {
        return chefService.getChefBySpecialtyChefs(name);
    }
}
