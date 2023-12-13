package net.workhub.payrollservice.repository;

import net.workhub.payrollservice.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository  extends JpaRepository<Payroll, Long> {
}
