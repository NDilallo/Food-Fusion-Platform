package com.foodFusion.foodFusionPlatform.services.homePage;

import java.util.List;

//import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Employees;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.EmployeesRepository;

@Service
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public Employees addEmployee(Employees employee) {
        return employeesRepository.save(employee);
    }

    public List<Employees> getAllEmployees() {
        return EmployeesRepository.findAll();
    }

    public Employees getEmployeeByName(String name) {
        return employeesRepository.findByName(name);
    }

    public List<Employees> getEmployeesByRole(String role) {
        return employeesRepository.findByRole(role);
    }
}
    // private final ChefsService chefsService;
    // private final WaiterService waiterService;

    // public EmployeesService(ChefsService chefsService, WaiterService waiterService) {
    //     this.chefsService = chefsService;
    //     this.waiterService = waiterService;
    // }

    // public String cook(String name, String role) {
    //     return chefsService.cook(name, role);
    // }

    // public String serve(String name, String role) {
    //     return waiterService.serve(name, role);
    // }

