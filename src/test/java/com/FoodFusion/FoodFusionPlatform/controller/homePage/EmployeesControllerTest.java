package com.foodFusion.foodFusionPlatform.controller.homePage;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Employees;
import com.foodFusion.foodFusionPlatform.services.homePage.EmployeesService;

@WebMvcTest(EmployeesController.class)
public class EmployeesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeesService employeesService;

    private Employees employee;

    @BeforeEach
    void setUp() {
        employee = new Employees(1L, "John Doe", "Waiter");
    }

    @Test
    void testAddEmployee() throws Exception {
        given(employeesService.addEmployee(employee)).willReturn(employee);

        mockMvc.perform(post("/employees/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"John Doe\",\"role\":\"Waiter\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        given(employeesService.getAllEmployees()).willReturn(Collections.singletonList(employee));

        mockMvc.perform(get("/employees/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testGetEmployeeByName() throws Exception {
        given(employeesService.getEmployeeByName("John Doe")).willReturn(employee);

        mockMvc.perform(get("/employees/name")
                .param("name", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetEmployeesByRole() throws Exception {
        given(employeesService.getEmployeesByRole("Waiter")).willReturn(Collections.singletonList(employee));

        mockMvc.perform(get("/employees/role")
                .param("role", "Waiter"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].role").value("Waiter"));
    }
}

