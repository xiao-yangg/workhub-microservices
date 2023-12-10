package net.workhub.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.workhub.departmentservice.dto.DepartmentDto;
import net.workhub.departmentservice.entity.Department;
import net.workhub.departmentservice.exception.ResourceNotFoundException;
import net.workhub.departmentservice.mapper.DepartmentMapper;
import net.workhub.departmentservice.repository.DepartmentRepository;
import net.workhub.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Department department = DepartmentMapper.mapToDepartment(departmentDto); // convert department dto to jpa entity

        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {

        Department department = departmentRepository.findByDepartmentCode(code).orElseThrow(
                () -> new ResourceNotFoundException("Department", "code", code)
        );

        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto updateDepartmentByCode(DepartmentDto departmentDto) {

        Department existingDepartment = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode()).orElseThrow(
                () -> new ResourceNotFoundException("Department", "code", departmentDto.getDepartmentCode())
        );

        existingDepartment.setDepartmentName(departmentDto.getDepartmentName());
        existingDepartment.setDepartmentDescription(departmentDto.getDepartmentDescription());

        Department savedDepartment = departmentRepository.save(existingDepartment);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void deleteDepartmentByCode(String code) {

        Department existingDepartment = departmentRepository.findByDepartmentCode(code).orElseThrow(
                () -> new ResourceNotFoundException("Department", "code", code)
        );

        departmentRepository.delete(existingDepartment);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map((DepartmentMapper::mapToDepartmentDto)).collect(Collectors.toList());
    }
}
