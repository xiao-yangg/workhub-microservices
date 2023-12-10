package net.workhub.departmentservice.service;

import net.workhub.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);

    DepartmentDto updateDepartmentByCode(DepartmentDto departmentDto);

    void deleteDepartmentByCode(String code);

    List<DepartmentDto> getAllDepartments();
}
