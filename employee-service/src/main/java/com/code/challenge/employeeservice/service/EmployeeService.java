package com.code.challenge.employeeservice.service;

import com.code.challenge.employeeservice.api.request.EmployeeRequest;
import com.code.challenge.employeeservice.dto.EmployeeDTO;
import com.code.challenge.employeeservice.dto.GenericResponseDTO;
import com.code.challenge.employeeservice.utils.EmployeeID;

/**
 * @author Ragab Belal
 */
public interface EmployeeService {

    GenericResponseDTO createEmployee(EmployeeRequest employeeRequest) throws Exception;

    GenericResponseDTO updateEmployee(EmployeeID employeeUUID, EmployeeRequest employeeRequest) throws Exception;

    GenericResponseDTO deleteEmployee(EmployeeID employeeUUID) throws Exception;

    EmployeeDTO getEmployeeByUUID(EmployeeID employeeUUID) throws Exception;
}
