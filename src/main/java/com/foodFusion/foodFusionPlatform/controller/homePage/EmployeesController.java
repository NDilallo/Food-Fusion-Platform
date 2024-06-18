package com.foodFusion.foodFusionPlatform.controller.homePage;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Employees;
import com.foodFusion.foodFusionPlatform.services.homePage.EmployeesService;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @PostMapping("/add")
    public Employees addEmployee(@RequestBody Employees employee) {
        return employeesService.addEmployee(employee);
    }

    @GetMapping("/all")
    public List<Employees> getAllEmployees() {
        return employeesService.getAllEmployees();
    }

    @GetMapping("/name")
    public Employees getEmployeeByName(@RequestParam String name) {
        return employeesService.getEmployeeByName(name);
    }

    @GetMapping("/role")
    public List<Employees> getEmployeesByRole(@RequestParam String role) {
        return employeesService.getEmployeesByRole(role);
    }
}

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
