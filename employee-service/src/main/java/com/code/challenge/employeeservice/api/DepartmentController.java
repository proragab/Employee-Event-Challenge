package com.code.challenge.employeeservice.api;

import com.code.challenge.employeeservice.api.request.DepartmentRequest;
import com.code.challenge.employeeservice.dto.GenericResponseDTO;
import com.code.challenge.employeeservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ragab Belal
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping
    public ResponseEntity<GenericResponseDTO> createDepartment(@RequestBody DepartmentRequest departmentRequest) throws Exception {
        GenericResponseDTO response = departmentService.createDepartment(departmentRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
