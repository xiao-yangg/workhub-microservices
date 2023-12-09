package net.workhub.employeeservice.mapper;

import net.workhub.employeeservice.dto.EmployeeDto;
import net.workhub.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {

        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
    }
}
