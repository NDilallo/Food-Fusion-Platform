package com.foodFusion.foodFusionPlatform.rdbm.homePage;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeesRepositoryTest {

    @Autowired
    private EmployeesRepository employeesRepository;

    private Employees employee;

    @BeforeEach
    void setUp() {
        employee = new Employees();
        employee.setName("John Doe");
        employee.setRole("Waiter");
    }

    @Test
    void testSaveEmployee() {
        Employees savedEmployee = employeesRepository.save(employee);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo("John Doe");
        assertThat(savedEmployee.getRole()).isEqualTo("Waiter");
    }

    @Test
    void testFindByName() {
        employeesRepository.save(employee);

        Employees foundEmployee = employeesRepository.findByName("John Doe");

        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getName()).isEqualTo("John Doe");
    }

    @Test
    void testFindAll() {
        employeesRepository.save(employee);

        List<Employees> employees = employeesRepository.findAll();

        assertThat(employees).isNotEmpty();
        assertThat(employees).hasSize(1);
        assertThat(employees.get(0).getName()).isEqualTo("John Doe");
    }

    @Test
    void testFindByRole() {
        employeesRepository.save(employee);

        List<Employees> employees = employeesRepository.findByRole("Waiter");

        assertThat(employees).isNotEmpty();
        assertThat(employees).hasSize(1);
        assertThat(employees.get(0).getRole()).isEqualTo("Waiter");
    }
}
