package com.example.sevice;

import com.example.dto.DtoToEmployee;
import com.example.dto.EmployeeToDto;
import com.example.entity.Employee;
import com.example.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeToDto addEmployee(DtoToEmployee dtoToEmployee) {
        Employee employee = convertToEmp(dtoToEmployee);
        Employee save = employeeRepo.save(employee);
        return convertToDto(save);
    }

    @Override
    public List<EmployeeToDto> getAllEmployee() {
        List<Employee> employeeList = employeeRepo.findAll();
        return employeeList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeToDto updateEmployee(Integer id, DtoToEmployee dtoToEmployee) {
        Employee emp = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        Employee convertToEmp = convertToEmp(dtoToEmployee);

        emp.setName(convertToEmp.getName());
        emp.setProfilePic(convertToEmp.getProfilePic());
        emp.setGender(convertToEmp.getGender());
        emp.setDepartments(convertToEmp.getDepartments());
        emp.setSalary(convertToEmp.getSalary());
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("d-MMMM-yyyy")
                .toFormatter(Locale.ENGLISH);
        emp.setStartDate(LocalDate.parse(dtoToEmployee.getStartDate(), formatter));
        emp.setNotes(convertToEmp.getNotes());

        return convertToDto(employeeRepo.save(emp));
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepo.deleteById(id);
    }

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

    @Override
    public EmployeeToDto getEmployeeById(Integer id) {
        Employee emp = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return convertToDto(emp);
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
