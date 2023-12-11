package net.workhub.employeeservice.mapper;

import net.workhub.employeeservice.dto.DepartmentDto;
import net.workhub.employeeservice.dto.EmployeeDetailDto;
import net.workhub.employeeservice.dto.EmployeeDto;
import net.workhub.employeeservice.dto.OrganizationDto;
import net.workhub.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEmployee(EmployeeDto employeeDto) {

        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );
    }

    public static EmployeeDto mapToEmployeeDto(Employee employee) {

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );
    }

    public static EmployeeDetailDto mapToEmployeeDetailDto(EmployeeDto employeeDto, DepartmentDto departmentDto, OrganizationDto organizationDto) {

        return new EmployeeDetailDto(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                departmentDto,
                employeeDto.getOrganizationCode(),
                organizationDto
        );
    }
}
