package com.FoodFusion.FoodFusionPlatform.rdbm.HomePage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/add")
    public String addChef(@RequestBody Chefs chef) {
        return chefService.addChef(chef);
    }

    @GetMapping("/all")
    public List<Chefs> getAllChefs() {
        return chefService.getAllChefs();
    }
}
