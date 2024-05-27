package com.foodFusion.foodFusionPlatform.controller.homePage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Employees;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.Employees.WaiterService;
import com.foodFusion.foodFusionPlatform.services.homePage.ChefsService;


@RestController
public class EmployeesController {

    // private final ChefsService chefsService;
    // private final WaiterService waiterService;

    // public EmployeesController(ChefsService chefsService, WaiterService waiterService) {
    //     this.chefsService = chefsService;
    //     this.waiterService = waiterService;
    // }

    // @PostMapping("/cook")
    // public String cook(@RequestBody Employees employees) {
    //     chefsService.cook(employees.getName(), employees.getRole());
    //     return employees.getName() + " is cooking unique dishes or not authorized to cook.";
    // }

    // @PostMapping("/serve")
    // public String serve(@RequestBody Employees employees) {
    //     waiterService.serve(employees.getName(), employees.getRole());
    //     return employees.getName() + " is serving unique dishes or not authorized to serve.";
    // }
}
