package com.FoodFusion.FoodFusionPlatform.rdbm.HomePage;

import org.springframework.stereotype.Service;

import com.FoodFusion.FoodFusionPlatform.rdbm.HomePage.Employees.WaiterService;

@Service
public class EmployeesService {

    private final ChefsService chefsService;
    private final WaiterService waiterService;

    public EmployeesService(ChefsService chefsService, WaiterService waiterService) {
        this.chefsService = chefsService;
        this.waiterService = waiterService;
    }

    public String cook(String name, String role) {
        return chefsService.cook(name, role);
    }

    public String serve(String name, String role) {
        return waiterService.serve(name, role);
    }
}
