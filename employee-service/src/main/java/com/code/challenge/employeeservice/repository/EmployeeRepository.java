package com.code.challenge.employeeservice.repository;

import com.code.challenge.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Ragab Belal
 */
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
