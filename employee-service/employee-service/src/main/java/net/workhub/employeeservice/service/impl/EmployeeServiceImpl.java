package net.workhub.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.workhub.employeeservice.dto.EmployeeDto;
import net.workhub.employeeservice.entity.Employee;
import net.workhub.employeeservice.exception.ResourceNotFoundException;
import net.workhub.employeeservice.mapper.EmployeeMapper;
import net.workhub.employeeservice.repository.EmployeeRepository;
import net.workhub.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(employeeDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeDto.getId())
        );

        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());

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
