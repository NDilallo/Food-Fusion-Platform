package com.foodFusion.foodFusionPlatform.controller.homePage;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Chefs;
import com.foodFusion.foodFusionPlatform.services.homePage.ChefsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chefs")
@RequiredArgsConstructor
public class ChefsController {

    private final ChefsService chefsService;

    @PostMapping("/add")
    public Chefs addChef(@RequestBody Chefs chef) {
        return chefsService.addChef(chef);
    }

    @GetMapping
    public List<Chefs> getAllChefs() {
        return chefsService.getAllChefs();
    }

    @GetMapping("/find")
    public Chefs getChefByName(@RequestParam String name) {
        return chefsService.getChefByName(name);
    }
}
