package com.example.controller;

import com.example.dto.DtoToEmployee;
import com.example.dto.EmployeeToDto;
import com.example.sevice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<EmployeeToDto> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @PostMapping("/addEmp")
    public EmployeeToDto addEmployee(@RequestBody DtoToEmployee dtoToEmployee) {
        return employeeService.addEmployee(dtoToEmployee);
    }

    @PutMapping("/updateEmp/{id}")
    public EmployeeToDto updateEmployee(@PathVariable Integer id, @RequestBody DtoToEmployee dtoToEmployee) {
        return employeeService.updateEmployee(id, dtoToEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/{id}")
    public EmployeeToDto getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }
}
