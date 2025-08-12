package com.example.sevice;

import com.example.dto.DtoToEmployee;
import com.example.dto.EmployeeToDto;

import java.util.List;

public interface EmployeeService {
    EmployeeToDto addEmployee(DtoToEmployee dtoToEmployee);

    List<EmployeeToDto> getAllEmployee();

    EmployeeToDto updateEmployee(Integer id, DtoToEmployee dtoToEmployee);

    void deleteEmployee(Integer id);

    EmployeeToDto getEmployeeById(Integer id);
}
