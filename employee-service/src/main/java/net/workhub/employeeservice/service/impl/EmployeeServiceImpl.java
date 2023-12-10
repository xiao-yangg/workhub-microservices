package net.workhub.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.workhub.employeeservice.dto.DepartmentDto;
import net.workhub.employeeservice.dto.EmployeeDetailDto;
import net.workhub.employeeservice.dto.EmployeeDto;
import net.workhub.employeeservice.entity.Employee;
import net.workhub.employeeservice.exception.ResourceNotFoundException;
import net.workhub.employeeservice.mapper.EmployeeMapper;
import net.workhub.employeeservice.repository.EmployeeRepository;
import net.workhub.employeeservice.service.APIClient;
import net.workhub.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // private WebClient webClient;
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDetailDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        return EmployeeMapper.mapToEmployeeDetailDto(employeeDto, departmentDto);
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
}
