package net.workhub.employeeservice.mapper;

import net.workhub.employeeservice.dto.DepartmentDto;
import net.workhub.employeeservice.dto.EmployeeDetailDto;
import net.workhub.employeeservice.dto.EmployeeDto;
import net.workhub.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEmployee(EmployeeDto employeeDto) {

        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
    }

    public static EmployeeDto mapToEmployeeDto(Employee employee) {

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
    }

    public static EmployeeDetailDto mapToEmployeeDetailDto(EmployeeDto employeeDto, DepartmentDto departmentDto) {

        return new EmployeeDetailDto(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                departmentDto
        );
    }
}
