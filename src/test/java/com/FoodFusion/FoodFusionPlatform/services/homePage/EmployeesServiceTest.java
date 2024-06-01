package com.foodFusion.foodFusionPlatform.services.homePage;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Employees;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.EmployeesRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeesServiceTest {

    @Mock
    private EmployeesRepository employeesRepository;

    @InjectMocks
    private EmployeesService employeesService;

    private Employees employee;

    @BeforeEach
    void setUp() {
        employee = new Employees(1L, "John Doe", "Chef");
    }

    @Test
    void testAddEmployee() {
        given(employeesRepository.save(employee)).willReturn(employee);

        Employees savedEmployee = employeesService.addEmployee(employee);

        assertThat(savedEmployee).isNotNull();
        verify(employeesRepository).save(employee);
    }

    @Test
    void testGetAllEmployees() {
        given(employeesRepository.findAll()).willReturn(Collections.singletonList(employee));

        List<Employees> employees = employeesService.getAllEmployees();

        assertThat(employees).hasSize(1);
        verify(employeesRepository).findAll();
    }

    @Test
    void testGetEmployeeByName() {
        given(employeesRepository.findByName("John Doe")).willReturn(employee);

        Employees foundEmployee = employeesService.getEmployeeByName("John Doe");

        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getName()).isEqualTo("John Doe");
        verify(employeesRepository).findByName("John Doe");
    }

    @Test
    void testGetEmployeesByRole() {
        given(employeesRepository.findByRole("Chef")).willReturn(Collections.singletonList(employee));

        List<Employees> employees = employeesService.getEmployeesByRole("Chef");

        assertThat(employees).hasSize(1);
        assertThat(employees.get(0).getRole()).isEqualTo("Chef");
        verify(employeesRepository).findByRole("Chef");
    }
}

