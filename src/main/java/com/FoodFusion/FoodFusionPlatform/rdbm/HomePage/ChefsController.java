package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@SpringBootApplication
public class ChefsController {

    public static void main(String[] args) {
        SpringApplication.run(ChefsController.class, args);
    }
}

@Data
// @NoArgsConstructor
// @AllArgsConstructor
// class Chef {
//     private String name;
//     private String specialty;
// }

@RestController
@RequestMapping("/chef")
class ChefController {

    private final List<Chefs> chefs = new ArrayList<>();

    @PostMapping("/add")
    public String addChef(@RequestBody Chefs chef) {
        chefs.add(chef);
        return "Chef added successfully!";
    }

    @GetMapping("/all")
    public List<Chefs> getAllChefs() {
        return chefs;
    }
}


