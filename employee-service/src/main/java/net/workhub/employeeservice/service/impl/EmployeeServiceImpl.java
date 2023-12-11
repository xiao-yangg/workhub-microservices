package net.workhub.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.workhub.employeeservice.dto.DepartmentDto;
import net.workhub.employeeservice.dto.EmployeeDetailDto;
import net.workhub.employeeservice.dto.EmployeeDto;
import net.workhub.employeeservice.dto.OrganizationDto;
import net.workhub.employeeservice.entity.Employee;
import net.workhub.employeeservice.exception.ResourceNotFoundException;
import net.workhub.employeeservice.mapper.EmployeeMapper;
import net.workhub.employeeservice.repository.EmployeeRepository;
import net.workhub.employeeservice.service.APIClient;
import net.workhub.employeeservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

    private WebClient webClient;
    // private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Retry(name="${spring.application.name}", fallbackMethod="getDefault")
    @CircuitBreaker(name="${spring.application.name}", fallbackMethod="getDefault")
    @Override
    public EmployeeDetailDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        // DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        // OrganizationDto organizationDto = apiClient.getOrganization(employee.getOrganizationCode());

        return EmployeeMapper.mapToEmployeeDetailDto(employeeDto, departmentDto, organizationDto);
    }

    @Override
    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto) {

        Employee existingEmployee = employeeRepository.findById(employeeDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeDto.getId())
        );

        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setDepartmentCode(employeeDto.getDepartmentCode());

        Employee savedEmployee = employeeRepository.save(existingEmployee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) {

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );

        employeeRepository.delete(existingEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((EmployeeMapper::mapToEmployeeDto)).collect(Collectors.toList());
    }

    public EmployeeDetailDto getDefault(Long id, Exception exception) {

        LOGGER.info("entering fallback method");

        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("unknown");
        departmentDto.setDepartmentCode("unknown");
        departmentDto.setDepartmentDescription("unknown");

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName("unknown");
        organizationDto.setOrganizationCode("unknown");
        organizationDto.setOrganizationDescription("unknown");

        return EmployeeMapper.mapToEmployeeDetailDto(employeeDto, departmentDto, organizationDto);
    }
}
