package com.example.utility;

import com.example.dto.DtoToEmployee;
import com.example.dto.EmployeeToDto;
import com.example.entity.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class EmployeeUtility {
    public Employee convertToEmp(DtoToEmployee dtoToEmployee){
        Employee employee = new Employee();
        employee.setName(dtoToEmployee.getName());
        employee.setProfilePic(dtoToEmployee.getProfilePic());
        employee.setGender(dtoToEmployee.getGender());
        employee.setDepartments(dtoToEmployee.getDepartments());
        employee.setSalary(dtoToEmployee.getSalary());
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("d-MMMM-yyyy")
                .toFormatter(Locale.ENGLISH);
        employee.setStartDate(LocalDate.parse(dtoToEmployee.getStartDate(), formatter));
        employee.setNotes(dtoToEmployee.getNotes());
        return employee;
    }

    public EmployeeToDto convertToDto(Employee employee){
        EmployeeToDto employeeToDto = new EmployeeToDto();
        employeeToDto.setEmpId(employee.getEmpId());
        employeeToDto.setName(employee.getName());
        employeeToDto.setProfilePic(employee.getProfilePic());
        employeeToDto.setGender(employee.getGender());
        employeeToDto.setDepartments(employee.getDepartments());
        employeeToDto.setSalary(employee.getSalary());
        employeeToDto.setStartDate(employee.getStartDate().format(DateTimeFormatter.ofPattern("d-MMMM-yyyy", Locale.ENGLISH)));
        employeeToDto.setNotes(employee.getNotes());
        return employeeToDto;
    }
}
