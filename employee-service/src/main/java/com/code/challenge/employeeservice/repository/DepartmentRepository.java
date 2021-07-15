package com.code.challenge.employeeservice.repository;

import com.code.challenge.employeeservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ragab Belal
 */
public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
