package net.workhub.departmentservice.controller;

import lombok.AllArgsConstructor;
import net.workhub.departmentservice.dto.DepartmentDto;
import net.workhub.departmentservice.entity.Department;
import net.workhub.departmentservice.service.DepartmentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    // Save department REST API
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED); // <response, code>
    }

    // Get department REST API
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode) {
        DepartmentDto department = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    // Update department REST API
    @PutMapping("{department-code}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("department-code") String departmentCode,
                                                          @RequestBody DepartmentDto departmentDto) {
        departmentDto.setDepartmentCode(departmentCode);
        DepartmentDto updatedDepartment = departmentService.updateDepartmentByCode(departmentDto);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    // Delete department REST API
    @DeleteMapping("{department-code}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("department-code") String departmentCode) {
        departmentService.deleteDepartmentByCode(departmentCode);
        return new ResponseEntity<>("Department successfully deleted!", HttpStatus.OK);
    }

    // List departments REST API
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
