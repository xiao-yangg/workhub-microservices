package net.workhub.employeeservice.service;

import net.workhub.employeeservice.dto.EmployeeDetailDto;
import net.workhub.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDetailDto getEmployeeById(Long id);

    EmployeeDto updateEmployeeById(EmployeeDto employeeDto);

    void deleteEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();
}
