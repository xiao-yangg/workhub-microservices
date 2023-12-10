package net.workhub.departmentservice.repository;

import net.workhub.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> { // <Entity, Id type>

    Optional<Department> findByDepartmentCode(String departmentCode);
}
