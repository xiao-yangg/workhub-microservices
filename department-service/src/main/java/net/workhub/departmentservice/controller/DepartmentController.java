package net.workhub.departmentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.workhub.departmentservice.dto.DepartmentDto;
import net.workhub.departmentservice.entity.Department;
import net.workhub.departmentservice.service.DepartmentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST API Swagger Documentation: http://localhost:8080/swagger-ui/index.html
@Tag(
        name="Department Service - DepartmentController",
        description="DepartmentController exposes REST APIs for Department-Service"
)
@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    // Save department REST API
    @Operation(
            summary="Save Department REST API",
            description="Save department object in database"
    )
    @ApiResponse(
            responseCode="201",
            description="HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED); // <response, code>
    }

    // Get department REST API
    @Operation(
            summary="Get Department REST API",
            description="Get department object in database"
    )
    @ApiResponse(
            responseCode="200",
            description="HTTP Status 200 OK"
    )
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode) {
        DepartmentDto department = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    // Update department REST API
    @Operation(
            summary="Update Department REST API",
            description="Update department object in database"
    )
    @ApiResponse(
            responseCode="200",
            description="HTTP Status 200 OK"
    )
    @PutMapping("{department-code}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("department-code") String departmentCode,
                                                          @RequestBody DepartmentDto departmentDto) {
        departmentDto.setDepartmentCode(departmentCode);
        DepartmentDto updatedDepartment = departmentService.updateDepartmentByCode(departmentDto);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    // Delete department REST API
    @Operation(
            summary="Delete Department REST API",
            description="Delete department object in database"
    )
    @ApiResponse(
            responseCode="200",
            description="HTTP Status 200 OK"
    )
    @DeleteMapping("{department-code}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("department-code") String departmentCode) {
        departmentService.deleteDepartmentByCode(departmentCode);
        return new ResponseEntity<>("Department successfully deleted!", HttpStatus.OK);
    }

    // List departments REST API
    @Operation(
            summary="List Departments REST API",
            description="List department objects in database"
    )
    @ApiResponse(
            responseCode="200",
            description="HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
