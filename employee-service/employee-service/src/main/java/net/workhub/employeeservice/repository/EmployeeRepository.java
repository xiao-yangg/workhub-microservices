package net.workhub.employeeservice.repository;

import net.workhub.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> { // inherit CRUD operation
}
