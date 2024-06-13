package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeesRepository extends CrudRepository<Employees, Long> {
    Employees findByName(String name);
    List<Employees> findAll();
    List<Employees> findByRole(String role);
}

