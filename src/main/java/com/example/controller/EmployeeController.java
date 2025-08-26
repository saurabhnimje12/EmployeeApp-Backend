package com.example.controller;

import com.example.dto.DtoToEmployee;
import com.example.dto.EmployeeToDto;
import com.example.sevice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EmployeeController handles HTTP requests related to Employee operations.
 * It provides endpoints for creating, reading, updating, and deleting employee records.
 * The controller uses EmployeeService to perform business logic and data manipulation.
 * CORS is configured to allow requests from http://localhost:5173.
 */

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieves a list of all employees.
     *
     * @return List of EmployeeToDto objects representing all employees.
     */
    @GetMapping("/")
    public List<EmployeeToDto> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    /**
     * Adds a new employee.
     *
     * @param dtoToEmployee Data transfer object containing employee details.
     * @return EmployeeToDto object representing the added employee.
     */
    @PostMapping("/addEmp")
    public EmployeeToDto addEmployee(@RequestBody DtoToEmployee dtoToEmployee) {
        return employeeService.addEmployee(dtoToEmployee);
    }

    /**
     * Updates an existing employee.
     *
     * @param id            The ID of the employee to update.
     * @param dtoToEmployee Data transfer object containing updated employee details.
     * @return EmployeeToDto object representing the updated employee.
     */
    @PutMapping("/updateEmp/{id}")
    public EmployeeToDto updateEmployee(@PathVariable Integer id, @RequestBody DtoToEmployee dtoToEmployee) {
        return employeeService.updateEmployee(id, dtoToEmployee);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id The ID of the employee to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return EmployeeToDto object representing the requested employee.
     */
    @GetMapping("/{id}")
    public EmployeeToDto getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }
}
