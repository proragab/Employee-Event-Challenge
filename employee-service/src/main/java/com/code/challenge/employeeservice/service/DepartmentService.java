package com.code.challenge.employeeservice.service;

import com.code.challenge.employeeservice.api.request.DepartmentRequest;
import com.code.challenge.employeeservice.dto.GenericResponseDTO;
import com.code.challenge.employeeservice.entity.Department;

/**
 * @author Ragab Belal
 */
public interface DepartmentService {

    GenericResponseDTO createDepartment(DepartmentRequest departmentRequest) throws Exception;

    Department getDepartmentById(Long departmentId) throws Exception;
}
