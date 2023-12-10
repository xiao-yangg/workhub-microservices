package net.workhub.employeeservice.controller;

import lombok.AllArgsConstructor;
import net.workhub.employeeservice.dto.EmployeeDetailDto;
import net.workhub.employeeservice.dto.EmployeeDto;
import net.workhub.employeeservice.exception.ErrorDetails;
import net.workhub.employeeservice.exception.ResourceNotFoundException;
import net.workhub.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    // Save employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Get employee REST API
    @GetMapping("{employee-id}")
    public ResponseEntity<EmployeeDetailDto> getEmployee(@PathVariable("employee-id") Long employeeId) {
        EmployeeDetailDto employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Update employee REST API
    @PutMapping("{employee-id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("employee-id") Long employeeId,
                                                      @RequestBody EmployeeDto employeeDto) {
        employeeDto.setId(employeeId);
        EmployeeDto updatedEmployee = employeeService.updateEmployeeById(employeeDto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    // Delete employee REST API
    @DeleteMapping("{employee-id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employee-id") Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
    }

    // List employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
